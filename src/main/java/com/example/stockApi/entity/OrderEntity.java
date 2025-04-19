package com.example.stockApi.entity;

import java.time.LocalDateTime;

import com.example.stockApi.enums.OrderType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

	private String stockId;
    private String userId;
    private OrderType orderType;
    private Long quantity;
    private Long orderStock;
    private Integer tradingResult;
    
    private Long price;

    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    
}
