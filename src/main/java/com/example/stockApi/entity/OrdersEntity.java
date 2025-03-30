package com.example.stockApi.entity;

import jakarta.persistence.*;

import jakarta.persistence.Entity;

@Entity
@Table(name = "orders")
public class OrdersEntity {
	
	@Id
    private Integer orderId;

    private String stockId;
    private Integer quantity;
    private Integer price;
    private Integer status;
    
    public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
    
    public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
