package com.example.stockApi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.stockApi.dto.OrderRequestDto;
import com.example.stockApi.dto.OrderResponseDto;
import com.example.stockApi.enums.OrderType;
import com.example.stockApi.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("removal")
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; 

    @MockBean
    private OrderService orderService;
	
	// 200
	@Test
	void shouldReturn200WhenCreateOrder() throws Exception {
		OrderRequestDto requestDto = new OrderRequestDto();
		requestDto.setUserId("aaaaa");
        requestDto.setStockCode("005930");
        requestDto.setOrderType(OrderType.BUY);
        requestDto.setQuantity(1);
        requestDto.setPrice(56000);
        
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
	@Disabled
	void shouldReturn400WhenCreateOrderByInvalidOrderID() throws Exception {
		OrderRequestDto invalidDto = new OrderRequestDto();
	    invalidDto.setUserId(""); // 빈 값
	    invalidDto.setStockCode("005930");
	    invalidDto.setOrderType(OrderType.BUY);
	    invalidDto.setQuantity(10);
	    invalidDto.setPrice(72000);

	    mockMvc.perform(post("/order")
	                    .contentType("application/json")
	                    .content(objectMapper.writeValueAsString(invalidDto)))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.message").value("유효하지 않은 사용자 ID"));
	}
	
	// 400
	// 유효하지 않은 주식 id
	@Test
	@Disabled
	void shouldReturn400WhenCreateOrderByInvalidStockID() throws Exception{
		OrderRequestDto invalidDto = new OrderRequestDto();
		invalidDto.setUserId("11111"); 
	    invalidDto.setStockCode("");	// 잘못된 주식 코드
	    invalidDto.setOrderType(OrderType.BUY);
	    invalidDto.setQuantity(10);
	    invalidDto.setPrice(72000);

	    mockMvc.perform(post("/order")
	                    .contentType("application/json")
	                    .content(objectMapper.writeValueAsString(invalidDto)))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.message").value("유효하지 않은 주식 id"));
	}
	
	// 400
	// 매입 금액이 부족
	@Test
	@Disabled
	void shouldReturn400WhenCreateOrderByInvalidBalance() throws Exception{
		OrderRequestDto invalidDto = new OrderRequestDto();
		invalidDto.setUserId("aaaaa"); 
	    invalidDto.setStockCode("005930");	
	    invalidDto.setOrderType(OrderType.BUY);
	    invalidDto.setQuantity(1000);
	    invalidDto.setPrice(72000);

	    mockMvc.perform(post("/order")
	                    .contentType("application/json")
	                    .content(objectMapper.writeValueAsString(invalidDto)))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.message").value("매입 금액이 부족"));
	}
	
	// 400
	// 매도 수량이 부족
	@Test
	@Disabled
	void shouldReturn400WhenCreateOrderByInvalidQuantity() throws Exception{
		OrderRequestDto invalidDto = new OrderRequestDto();
		invalidDto.setUserId("aaaaa"); 
	    invalidDto.setStockCode("005930");	
	    invalidDto.setOrderType(OrderType.SELL);
	    invalidDto.setQuantity(10);
	    invalidDto.setPrice(72000);

	    mockMvc.perform(post("/order")
	                    .contentType("application/json")
	                    .content(objectMapper.writeValueAsString(invalidDto)))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.message").value("매도 수량이 부족"));
	}
	
	// 400
	// 금액 설정 오류
	@Test
	@Disabled
	void shouldReturn400WhenCreateOrderByInvalidPrice() throws Exception{
		OrderRequestDto invalidDto = new OrderRequestDto();
		invalidDto.setUserId(""); 
	    invalidDto.setStockCode("");	
	    invalidDto.setOrderType(OrderType.BUY);
	    invalidDto.setQuantity(10);
	    invalidDto.setPrice(72000);

	    mockMvc.perform(post("/order")
	                    .contentType("application/json")
	                    .content(objectMapper.writeValueAsString(invalidDto)))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.message").value("금액 설정 오류"));
	}
}
