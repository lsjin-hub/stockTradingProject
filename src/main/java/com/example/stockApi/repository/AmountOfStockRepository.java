package com.example.stockApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.stockApi.entity.AmountOfStock;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AmountOfStockRepository {
	@PersistenceContext
    private EntityManager em;

    public Optional<AmountOfStock> findByUserIdAndStockId(String userId, String stockId) {
    	List<AmountOfStock> result = em.createQuery(
    	        "SELECT a FROM AmountOfStock a WHERE a.id.userId = :userId AND a.id.stockId = :stockId", AmountOfStock.class)
    	        .setParameter("userId", userId)
    	        .setParameter("stockId", stockId)
    	        .getResultList();

    	return result.stream().findFirst();
    }
    
    public AmountOfStock save(AmountOfStock stock) {
        if (stock.getId() == null) {
            em.persist(stock);
            return stock;
        } else {
            return em.merge(stock);
        }
    }
}
