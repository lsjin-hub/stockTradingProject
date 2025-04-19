package com.example.stockApi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.stockApi.dto.OrdersResponseDto;
import com.example.stockApi.dto.StocksDto;
import com.example.stockApi.entity.OrdersEntity;
import com.example.stockApi.entity.StockEntity;

public class OrdersMapper {
	// OrdersEntity 리스트를 OrderResponseDto 리스트로 변환
	public static OrdersResponseDto toDto(OrdersEntity ordersEntity) {
        List<StocksDto> stocks = ordersEntity.getStocks().stream()
            .map(OrdersMapper::toStocksDto)
            .collect(Collectors.toList());

        return new OrdersResponseDto(
        	ordersEntity.getOrderId(),
        	ordersEntity.getUserId(),
        	ordersEntity.getUserName(),
        	ordersEntity.getOrderDate(),
        	ordersEntity.getUpdatedDate(),
        	stocks
        );
    }

	// 주문 리스트 변환
    public static List<OrdersResponseDto> toDtoList(List<OrdersEntity> orderEntities) {
        return orderEntities.stream()
            .map(OrdersMapper::toDto)
            .collect(Collectors.toList());
    }
    
    private static StocksDto toStocksDto(StockEntity entity) {
        return new StocksDto(
            entity.getOrderType(),
            entity.getStockCode(),
            entity.getQuantity(),
            entity.getPrice(),
            entity.getTradingResult(),
            entity.getTradingDate().toString()  
        );
    }
}
