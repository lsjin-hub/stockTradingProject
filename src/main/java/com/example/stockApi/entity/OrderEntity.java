package com.example.stockApi.entity;

import java.time.LocalDateTime;

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
    private Integer orderType;
    private Long quantity;
    private Long orderStock;
    private Integer tradingResult;

    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<StockEntity> stocks;
//    
//    public Integer getOrderId() {
//		return orderId;
//	}
//	public void setOrderId(Integer orderId) {
//		this.orderId = orderId;
//	}
//	
//	public String getStockId() {
//		return stockId;
//	}
//	public void setStockId(String stockId) {
//		this.stockId = stockId;
//	}
//    
//    public Integer getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//	
//	public Integer getPrice() {
//		return price;
//	}
//	public void setPrice(Integer price) {
//		this.price = price;
//	}
//	
//	public Integer getStatus() {
//		return status;
//	}
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
//	public List<StockEntity> getStocks() { return stocks; }
//    public void setStocks(List<StockEntity> stocks) { this.stocks = stocks; }
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getOrderDate() {
//		return orderDate;
//	}
//	public void setOrderDate(String orderDate) {
//		this.orderDate = orderDate;
//	}
//	public String getUpdatedDate() {
//		return updatedDate;
//	}
//	public void setUpdatedDate(String updatedDate) {
//		this.updatedDate = updatedDate;
//	}
	
}
