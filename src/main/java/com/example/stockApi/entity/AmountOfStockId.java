package com.example.stockApi.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmountOfStockId implements Serializable{
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "stock_id")
	private String stockId;
}
