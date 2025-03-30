package com.example.stockApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.stockApi.entity.OrdersEntity;
import com.example.stockApi.repository.OrdersRepository;
import com.exmaple.stockApi.dto.OrdersResponseDto;

@Service
public class OrdersService {
	
	 private final OrdersRepository ordersRepository;

	    public OrdersService(OrdersRepository ordersRepository) {
	        this.ordersRepository = ordersRepository;
	    }
	    
	    public List<OrdersEntity> getAllOrders() {
	        return ordersRepository.findAll();
	    }

	    public OrdersEntity  getOrderById(Integer orderId) {
	    	return ordersRepository.findById(orderId);
	    }

}
