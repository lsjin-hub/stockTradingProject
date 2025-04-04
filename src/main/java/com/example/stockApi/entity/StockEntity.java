package com.example.stockApi.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class StockEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderType;

    @Column(nullable = false)
    private String stockCode;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    // 거래 상태
    @Column(nullable = false)
    private String tradingResult;

    // 거래 일자
    @Column(nullable = false)
    private LocalDate tradingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrdersEntity order;

    // 기본 생성자
    public StockEntity() {}

    // 전체 생성자
    public StockEntity(String orderType, String stockCode, Integer quantity, Integer price, String tradingResult, LocalDate tradingDate, OrdersEntity order) {
        this.orderType = orderType;
        this.stockCode = stockCode;
        this.quantity = quantity;
        this.price = price;
        this.tradingResult = tradingResult;
        this.tradingDate = tradingDate;
        this.order = order;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public String getStockCode() { return stockCode; }
    public void setStockCode(String stockCode) { this.stockCode = stockCode; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public String getTradingResult() { return tradingResult; }
    public void setTradingResult(String tradingResult) { this.tradingResult = tradingResult; }

    public LocalDate getTradingDate() { return tradingDate; }
    public void setTradingDate(LocalDate tradingDate) { this.tradingDate = tradingDate; }

    public OrdersEntity getOrder() { return order; }
    public void setOrder(OrdersEntity order) { this.order = order; }
}