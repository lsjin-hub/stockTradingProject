package com.example.stockApi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "account_bank")
    private String accountBank;

    @Column(name = "real_balance")
    private Long realBalance;

    @Column(name = "active_balance")
    private Long activeBalance;
}
