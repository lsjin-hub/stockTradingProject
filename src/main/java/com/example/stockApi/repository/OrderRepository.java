package com.example.stockApi.repository;

import org.springframework.stereotype.Repository;

import com.example.stockApi.entity.OrderEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class OrderRepository{
	
	@PersistenceContext
    private EntityManager em;
	
	// 주문 생성
    public void save(OrderEntity order) {
        em.persist(order);
    }
}
