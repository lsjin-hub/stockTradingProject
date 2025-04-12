package com.example.stockApi.dto;

import com.example.stockApi.enums.OrderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
	private String userId;
	private String stockCode;
    private OrderType orderType;
    private Integer quantity;
    private Integer price;
  
}
