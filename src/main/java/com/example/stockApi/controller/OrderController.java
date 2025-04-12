package com.example.stockApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.exmaple.stockApi.dto.OrderRequestDto;
import com.exmaple.stockApi.dto.OrderResponseDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class OrderController {

	@PostMapping("/order")
	public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto request) {
		// 유효성 체크 샘플
        if (request.getUserId() == null || request.getUserId().isBlank()) {
            return ResponseEntity.badRequest().body(new OrderResponseDto(null, "유효하지 않은 사용자 ID"));
        }

        if (request.getStockCode() == null || request.getStockCode().isBlank()) {
            return ResponseEntity.badRequest().body(new OrderResponseDto(null, "유효하지 않은 주식 ID"));
        }

        if (request.getPrice() == null || request.getPrice() <= 0) {
            return ResponseEntity.badRequest().body(new OrderResponseDto(null, "유효하지 않은 금액입니다."));
        }

        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            return ResponseEntity.badRequest().body(new OrderResponseDto(null, "유효하지 않은 수량입니다."));
        }
        
        // 사용자/주식 ID 체크 로직
        boolean userIdOk = true;
        boolean stockCodeOk = true;
        
        if (!userIdOk) {
        	return ResponseEntity.badRequest().body(new OrderResponseDto(null, "유효하지 않은 사용자 ID"));
        }

        if (!stockCodeOk) {
        	return ResponseEntity.badRequest().body(new OrderResponseDto(null, "유효하지 않은 주식 ID"));
        }

        // 잔고/재고 체크 로직
        boolean balanceOk = true;
        boolean quantityOk = true;
        
        // service 호출

        if (!balanceOk) {
            return ResponseEntity.badRequest().body(new OrderResponseDto(null, "잔고 부족"));
        }

        if (!quantityOk) {
            return ResponseEntity.badRequest().body(new OrderResponseDto(null, "매도 수량 부족"));
        }

        // 성공 처리
        OrderResponseDto response = new OrderResponseDto();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
