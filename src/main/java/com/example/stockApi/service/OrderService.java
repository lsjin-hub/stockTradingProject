package com.example.stockApi.service;

import com.example.stockApi.dto.OrderRequestDto;
import com.example.stockApi.dto.OrderResponseDto;

public interface OrderService {
	
	// Order 등록
	OrderResponseDto createOrder (OrderRequestDto requestDto); 

}
