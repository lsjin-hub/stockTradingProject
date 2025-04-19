package com.example.stockApi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "amount_of_stock")
public class AmountOfStock {
	@EmbeddedId
    private AmountOfStockId id;

    @Column(name = "buy_stock_amount")
    private Long buyStockAmount;

    @Column(name = "buy_stock_price")
    private Long buyStockPrice;

    @Column(name = "active_amount")
    private Long activeAmount;

}
