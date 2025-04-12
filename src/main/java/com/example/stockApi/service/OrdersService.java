package com.example.stockApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.stockApi.dto.OrdersResponseDto;
import com.example.stockApi.entity.OrdersEntity;
import com.example.stockApi.mapper.OrdersMapper;
import com.example.stockApi.repository.OrdersRepository;

@Service
public class OrdersService {
	
	 private final OrdersRepository ordersRepository;

	    public OrdersService(OrdersRepository ordersRepository) {
	        this.ordersRepository = ordersRepository;
	    }
	    
	    public List<OrdersResponseDto> getAllOrders() {
	    	List<OrdersEntity> entities = ordersRepository.findAll();
	    	return OrdersMapper.toDtoList(entities);
	    }

	    public OrdersResponseDto getOrderById(Integer orderId) {
			Optional<OrdersEntity> optionalOrder = Optional.ofNullable(ordersRepository.findById(orderId));
			OrdersEntity entity = optionalOrder.orElseThrow(() -> new RuntimeException(""+orderId));
			return OrdersMapper.toDto(entity);
	    }

}
