package com.example.stockApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class ordersController {

	@GetMapping
	public void getOrders() {
		return;
	}
	
	@GetMapping
	public void getOrderById(@PathVariable("order_id") Long orderId) {
		return;
	}

}
