package com.exmaple.stockApi.dto;

public class StocksDto{
	
	private String orderType; // sell or buy
    private String stockCode;
    private Integer quantity;
    private Integer price;
    private String tradingResult; // 예: progress, completed 등
    private String tradingDate;
    
    // 기본 생성자
    public StocksDto() {}

    // 전체 필드 생성자
    public StocksDto(String orderType, String stockCode, Integer quantity, Integer price, String tradingResult, String tradingDate) {
    	
    	this.setOrderType(orderType);
    	this.setStockCode(stockCode);
    	this.setQuantity(quantity);
    	this.setPrice(price);
    	this.setTradingDate(tradingDate);
    	this.setTradingResult(tradingResult);
    	
    }
    
    // Getters and Setters
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTradingResult() {
		return tradingResult;
	}

	public void setTradingResult(String tradingResult) {
		this.tradingResult = tradingResult;
	}

	public String getTradingDate() {
		return tradingDate;
	}

	public void setTradingDate(String tradingDate) {
		this.tradingDate = tradingDate;
	}
    
}
