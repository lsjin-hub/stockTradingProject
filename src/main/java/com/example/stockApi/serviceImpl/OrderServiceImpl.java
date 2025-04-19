package com.example.stockApi.serviceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.stockApi.dto.OrderRequestDto;
import com.example.stockApi.dto.OrderResponseDto;
import com.example.stockApi.entity.Account;
import com.example.stockApi.entity.AmountOfStock;
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
    	        .findByUserIdAndStockId(requestDto.getUserId(), requestDto.getStockCode());

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
		
        orderRepository.save(entity);
		
		// 응답
		return OrderResponseDto.builder()
				.orderId(000111) // 주문 ID
				.message("주문이 성공적으로 처리되었습니다.")
				.build();
	}
	
}
