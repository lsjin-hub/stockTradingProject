package com.exmaple.stockApi.dto;

public class OrderResponseDto {
	
	private Integer orderId;
    private String message;
    
    // 기본 생성자
    public OrderResponseDto() {}

    // 전체 필드 생성자
    public OrderResponseDto(Integer orderId, String message) {
        this.orderId = orderId;
        this.message = message;
    }
    
    // Getters and Setters
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
