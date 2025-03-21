package com.example.stockApi.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(ordersController.class)
public class ordersControllerTest {
	
	
	// 200
	@Test
	@Disabled
	void shouldReturn200WhenCreateOrder() {
		
	}
	
	// 400
	// 유효하지 않은 주문 id
	@Test
	@Disabled
	void shouldReturn400WhenCreateOrderByInvalidOrderID() {
		
	}
	
	// 400
	// 유효하지 않은 주식 id
	@Test
	@Disabled
	void shouldReturn400WhenCreateOrderByInvalidStockID() {
		
	}
	
	// 400
	// 매입 금액이 부족
	@Test
	@Disabled
	void shouldReturn400WhenCreateOrderByInvalidBalance() {
		
	}
	
	// 400
	// 매도 수량이 부족
	@Test
	@Disabled
	void shouldReturn400WhenCreateOrderByInvalidQuantity() {
		
	}
	
	// 400
	// 금액 설정 오류
	@Test
	@Disabled
	void shouldReturn400WhenCreateOrderByInvalidPrice() {
		
	}
	
}
