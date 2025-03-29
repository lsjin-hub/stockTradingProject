package com.example.stockApi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.exmaple.stockApi.dto.OrderResponseDto;

@RestController
public class OrdersController {

	@GetMapping("/orders")
	public List<OrderResponseDto> getOrders() {
		
		List<OrderResponseDto> orders = new ArrayList<>();

	    orders.add(new OrderResponseDto());

	    orders.add(new OrderResponseDto());
	    
		return orders;
	}
	
	@GetMapping("/orders/{order_id}")
	public OrderResponseDto getOrderById(@PathVariable("order_id") Long orderId) {
		return new OrderResponseDto();
	}

}
