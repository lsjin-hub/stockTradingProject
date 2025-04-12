package com.example.stockApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StocksDto{
	
	private String orderType; // sell or buy
    private String stockCode;
    private Integer quantity;
    private Integer price;
    private String tradingResult; // 예: progress, completed 등
    private String tradingDate;
    
}
