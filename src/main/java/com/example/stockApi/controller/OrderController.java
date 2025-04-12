package com.example.stockApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stockApi.dto.OrderRequestDto;
import com.example.stockApi.dto.OrderResponseDto;
import com.example.stockApi.service.OrderService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

	@PostMapping("/order")
	public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto request) {
		System.out.println("userId : " + request.getUserId());
		
		// 유효성 체크 샘플
        if (request.getUserId() == null || request.getUserId().isBlank()) {
            return ResponseEntity.badRequest().body(
            		OrderResponseDto.builder()
            		.orderId(null)
            		.message("유효하지 않은 사용자 ID")
            		.build()
            );
        }

        if (request.getStockCode() == null || request.getStockCode().isBlank()) {
            return ResponseEntity.badRequest().body(
            		OrderResponseDto.builder()
            		.orderId(null)
            		.message("유효하지 않은 주식 ID")
            		.build()
            );
        }

        if (request.getPrice() == null || request.getPrice() <= 0) {
            return ResponseEntity.badRequest().body(
            		OrderResponseDto.builder()
            		.orderId(null)
            		.message("유효하지 않은 금액")
            		.build()
            );
        }

        if (request.getQuantity() == null || request.getQuantity() <= 0) {
        	return ResponseEntity.badRequest().body(
            		OrderResponseDto.builder()
            		.orderId(null)
            		.message("유효하지 않은 수량")
            		.build()
            );
        }
        
        // 사용자/주식 ID 체크 로직
        boolean userIdOk = true;
        boolean stockCodeOk = true;
        
        if (!userIdOk) {
        	return ResponseEntity.badRequest().body(
            		OrderResponseDto.builder()
            		.orderId(null)
            		.message("유효하지 않은 사용자 ID")
            		.build()
            );
        }

        if (!stockCodeOk) {
        	return ResponseEntity.badRequest().body(
            		OrderResponseDto.builder()
            		.orderId(null)
            		.message("유효하지 않은 주식 ID")
            		.build()
            );
        }

        // service 호출
        OrderResponseDto response = orderService.createOrder(request);
        System.out.println("response" + response);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
