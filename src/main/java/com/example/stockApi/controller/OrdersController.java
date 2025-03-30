package com.example.stockApi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.stockApi.entity.OrdersEntity;
import com.example.stockApi.service.OrdersService;

@RestController
public class OrdersController {

	private final OrdersService ordersService;
	
	public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
	
	@GetMapping("/orders")
	public List<OrdersEntity> getOrders() {	    
		return ordersService.getAllOrders();
	}
	
	@GetMapping("/orders/{order_id}")
	public OrdersEntity getOrderById(@PathVariable("order_id") Integer orderId) {
		return ordersService.getOrderById(orderId);
	}

}
