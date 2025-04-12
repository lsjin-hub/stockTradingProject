package com.example.stockApi.service;

import org.springframework.stereotype.Service;

import com.example.stockApi.dto.OrderRequestDto;
import com.example.stockApi.dto.OrderResponseDto;

@Service
public interface OrderService {
	
	// Order 등록
	OrderResponseDto createOrder (OrderRequestDto requestDto); 

}
