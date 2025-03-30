package com.exmaple.stockApi.dto;

import java.util.List;

public class OrdersResponseDto {
	
	private Integer orderId;
    private String userId;
    private	String userName;
    private String orderDate;
    private String updatedDate;
    private List<StocksDto> stocks;
    
    // 기본 생성자
    public OrdersResponseDto() {}

    // 전체 필드 생성자
    public OrdersResponseDto(Integer orderId, String userId, String userName, String orderDate, String updatedDate, List<StocksDto> stocks) {
        this.setOrderId(orderId);
        this.setUserId(userId);
        this.setUserName(userName);
        this.setOrderDate(orderDate);
        this.setUpdatedDate(updatedDate);
        this.setStocks(stocks);
    }
    
    // Getters and Setters
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<StocksDto> getStocks() {
		return stocks;
	}

	public void setStocks(List<StocksDto> stocks) {
		this.stocks = stocks;
	}

}
