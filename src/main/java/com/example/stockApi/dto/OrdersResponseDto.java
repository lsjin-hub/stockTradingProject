package com.example.stockApi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponseDto {
	
	private Integer orderId;
    private String userId;
    private	String userName;
    private String orderDate;
    private String updatedDate;
    private List<StocksDto> stocks;

}
