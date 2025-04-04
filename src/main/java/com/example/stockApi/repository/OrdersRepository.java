package com.example.stockApi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.stockApi.entity.OrdersEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class OrdersRepository {
	
	@PersistenceContext
    private EntityManager em;
	
	public List<OrdersEntity> findAll() {
        return em.createQuery("SELECT o FROM OrdersEntity o", OrdersEntity.class)
                 .getResultList();
    }
	
	public OrdersEntity findById(Integer id) {
        return em.find(OrdersEntity.class, id);
    }

    public void save(OrdersEntity order) {
        em.persist(order); 
    }

    public void update(OrdersEntity order) {
        em.merge(order); 
    }

    public void delete(Integer id) {
        OrdersEntity order = findById(id);
        if (order != null) {
            em.remove(order);
        }
    }

}
