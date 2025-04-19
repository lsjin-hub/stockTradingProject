package com.example.stockApi.serviceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.stockApi.dto.OrderRequestDto;
import com.example.stockApi.dto.OrderResponseDto;
import com.example.stockApi.entity.Account;
import com.example.stockApi.entity.AmountOfStock;
import com.example.stockApi.entity.AmountOfStockId;
import com.example.stockApi.entity.OrderEntity;
import com.example.stockApi.enums.OrderType;
import com.example.stockApi.repository.AccountRepository;
import com.example.stockApi.repository.AmountOfStockRepository;
import com.example.stockApi.repository.OrderRepository;
import com.example.stockApi.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private OrderRepository orderRepository;
	private final AccountRepository accountRepository;
	private final AmountOfStockRepository amountOfStockRepository;
	
    public OrderServiceImpl(OrderRepository orderRepository,
    		AccountRepository accountRepository,
            AmountOfStockRepository amountOfStockRepository) {
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
        this.amountOfStockRepository = amountOfStockRepository;
    }
    
	
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
    	
    	// 1. 계좌 조회
    	Optional<Account> accountOpt = accountRepository.findByUserId(requestDto.getUserId());
    	Account account = accountOpt.get();
    	
    	// 2. 보유 주식 조회
    	Optional<AmountOfStock> stockOpt = amountOfStockRepository
    	        .findByUserIdAndStockId(requestDto.getUserId(), requestDto.getStockId());

    	long totalPrice = requestDto.getQuantity() * requestDto.getPrice();
    	System.out.println("totalPrice : "+ totalPrice);
    	
    	// 3. 조건 체크
        if (requestDto.getOrderType() == OrderType.BUY) {
        	// 매입인 경우
            if (account.getActiveBalance() < totalPrice) {
                return OrderResponseDto.builder()
                    .message("매입 금액이 부족")
                    .build();
            }
        } else if (requestDto.getOrderType() == OrderType.SELL) {
            long ownedQuantity = stockOpt.map(AmountOfStock::getActiveAmount).orElse(0L);
            if (ownedQuantity < requestDto.getQuantity()) {
                return OrderResponseDto.builder()
                    .message("매도 수량이 부족")
                    .build();
            }
        }
    	
        // 4. 주문 등록
    	OrderEntity entity = new OrderEntity();
    	
    	entity.setUserId(requestDto.getUserId());
    	entity.setStockId(requestDto.getStockId());
    	entity.setOrderType(requestDto.getOrderType());
    	entity.setQuantity(requestDto.getQuantity());
    	entity.setPrice(requestDto.getPrice());
    	
        orderRepository.save(entity);
        
        // 5. 변경 수행
        if (requestDto.getOrderType() == OrderType.BUY) {
            // 잔고 차감
            account.setActiveBalance(account.getActiveBalance() - totalPrice);

            // 주식 보유량 증가
            AmountOfStock amountOfStock = stockOpt.orElseGet(() -> {
                AmountOfStock newStock = new AmountOfStock();
                
                AmountOfStockId newId = new AmountOfStockId();
                newId.setUserId(requestDto.getUserId());
                newId.setStockId(requestDto.getStockId());
                
                newStock.setId(newId);
                newStock.setActiveAmount(0L);
                return newStock;
            });

            amountOfStock.setActiveAmount(amountOfStock.getActiveAmount() + requestDto.getQuantity());
            amountOfStockRepository.save(amountOfStock);

        } else if (requestDto.getOrderType() == OrderType.SELL) {
            // 잔고 증가
            account.setActiveBalance(account.getActiveBalance() + totalPrice);

            // 주식 보유량 차감
            AmountOfStock amountOfStock = stockOpt.get(); 
            amountOfStock.setActiveAmount(amountOfStock.getActiveAmount() - requestDto.getQuantity());
            amountOfStockRepository.save(amountOfStock);
        }
		
		// 응답
		return OrderResponseDto.builder()
				.orderId(000111) // 주문 ID
				.message("주문이 성공적으로 처리되었습니다.")
				.build();
	}
	
}
