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
	private String stockId;
    private OrderType orderType;
    private Long quantity;
    private Long price;
  
}
