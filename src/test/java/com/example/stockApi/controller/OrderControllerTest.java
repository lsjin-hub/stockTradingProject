package com.example.stockApi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import com.example.stockApi.dto.OrderRequestDto;
import com.example.stockApi.dto.OrderResponseDto;
import com.example.stockApi.enums.OrderType;
import com.example.stockApi.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@SuppressWarnings("removal")
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; 

    @Autowired
    private OrderService orderService;
	
	// 200
	@Test
	void shouldReturn200WhenCreateOrder() throws Exception {
		OrderRequestDto requestDto = new OrderRequestDto();
		requestDto.setUserId("aaaaa");
        requestDto.setStockId("005930");
        requestDto.setOrderType(OrderType.BUY);
        requestDto.setQuantity(1L);
        requestDto.setPrice(56000L);
        
        System.out.println("test::::userId : " + requestDto.getUserId());
        
        OrderResponseDto mockResponse = OrderResponseDto.builder()
                .orderId(1)
                .message("주문이 성공적으로 처리되었습니다.")
                .build();

        Mockito.when(orderService.createOrder(Mockito.any()))
               .thenReturn(mockResponse);
        
        mockMvc.perform(post("/order")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1))
                .andExpect(jsonPath("$.message").value("주문이 성공적으로 처리되었습니다."));
	}
	
	// 400
	// 유효하지 않은 주문 id
	@Test
	void shouldReturn400WhenCreateOrderByInvalidOrderID() throws Exception {
		OrderRequestDto invalidDto = new OrderRequestDto();
	    invalidDto.setUserId(""); // 빈 값
	    invalidDto.setStockId("005930");
	    invalidDto.setOrderType(OrderType.BUY);
	    invalidDto.setQuantity(10L);
	    invalidDto.setPrice(72000L);

	    mockMvc.perform(post("/order")
	                    .contentType("application/json")
	                    .content(objectMapper.writeValueAsString(invalidDto)))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.message").value("유효하지 않은 사용자 ID"));
	}
	
	// 400
	// 유효하지 않은 주식 id
	@Test
	void shouldReturn400WhenCreateOrderByInvalidStockID() throws Exception{
		OrderRequestDto invalidDto = new OrderRequestDto();
		invalidDto.setUserId("11111"); 
	    invalidDto.setStockId("");	// 잘못된 주식 코드
	    invalidDto.setOrderType(OrderType.BUY);
	    invalidDto.setQuantity(10L);
	    invalidDto.setPrice(72000L);

	    mockMvc.perform(post("/order")
	                    .contentType("application/json")
	                    .content(objectMapper.writeValueAsString(invalidDto)))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.message").value("유효하지 않은 주식 ID"));
	}
	
	// 400
	// 매입 금액이 부족
	@Test
	void shouldReturn400WhenCreateOrderByInvalidBalance() throws Exception{
		OrderRequestDto invalidDto = new OrderRequestDto();
		invalidDto.setUserId("aaaaa"); 
	    invalidDto.setStockId("005930");	
	    invalidDto.setOrderType(OrderType.BUY);
	    invalidDto.setQuantity(1000L);
	    invalidDto.setPrice(72000L);

	    Mockito.when(orderService.createOrder(Mockito.any()))
        		.thenReturn(OrderResponseDto.builder()
            .orderId(null)
            .message("매입 금액이 부족")
            .build());
	    
	    mockMvc.perform(post("/order")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(invalidDto)))
	        .andExpect(status().isBadRequest())
	        .andExpect(jsonPath("$.message").value("매입 금액이 부족"));
	}
	
	// 400
	// 매도 수량이 부족
	@Test
	void shouldReturn400WhenCreateOrderByInvalidQuantity() throws Exception{
		OrderRequestDto invalidDto = new OrderRequestDto();
		invalidDto.setUserId("aaaaa"); 
	    invalidDto.setStockId("005930");	
	    invalidDto.setOrderType(OrderType.SELL);
	    invalidDto.setQuantity(10L);
	    invalidDto.setPrice(72000L);
	    
	    Mockito.when(orderService.createOrder(Mockito.any()))
	       .thenReturn(OrderResponseDto.builder()
	           .orderId(null)
	           .message("매도 수량이 부족")
	           .build());

	    mockMvc.perform(post("/order")
	                    .contentType("application/json")
	                    .content(objectMapper.writeValueAsString(invalidDto)))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.message").value("매도 수량이 부족"));
	}
	
	// 400
	// 금액 설정 오류
	@Test
	void shouldReturn400WhenCreateOrderByInvalidPrice() throws Exception{
		OrderRequestDto invalidDto = new OrderRequestDto();
		invalidDto.setUserId("aaaaa"); 
	    invalidDto.setStockId("005930");	
	    invalidDto.setOrderType(OrderType.BUY);
	    invalidDto.setQuantity(10L);
	    invalidDto.setPrice(-10L);

	    mockMvc.perform(post("/order")
	                    .contentType("application/json")
	                    .content(objectMapper.writeValueAsString(invalidDto)))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.message").value("유효하지 않은 금액"));
	}
	
	@Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
    void shouldInsertRealDataIntoDatabase() {
		try {
	        OrderRequestDto requestDto = new OrderRequestDto();
	        requestDto.setUserId("aaaaa");
	        requestDto.setStockId("005930");
	        requestDto.setOrderType(OrderType.BUY);
	        requestDto.setQuantity(1L);
	        requestDto.setPrice(56000L);
	
	        OrderResponseDto response = orderService.createOrder(requestDto);
	        System.out.println("주문 결과: " + response);
		} catch (Exception e) {
	        e.printStackTrace(); // 내부 예외 확인
	    }
    }
}
