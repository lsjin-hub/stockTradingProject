package com.exmaple.stockApi.dto;


public class OrderRequestDto {
	private String userId;
	private String stockCode;
    private String orderType;
    private Integer quantity;
    private Integer price;

    // 기본 생성자
    public OrderRequestDto() {}

    // 전체 필드 생성자
    public OrderRequestDto(String userId, String stockCode, String orderType, Integer quantity, Integer price) {
        this.setUserId(userId);
        this.setStockCode(stockCode);
        this.setOrderType(orderType);
        this.setQuantity(quantity);
        this.setPrice(price);
    }

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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

    
}
