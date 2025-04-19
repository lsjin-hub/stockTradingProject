package com.example.stockApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.stockApi.entity.Account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AccountRepository {
	
	@PersistenceContext
    private EntityManager em;

    public Optional<Account> findByUserId(String userId) {
    	 List<Account> result = em.createQuery(
    		        "SELECT a FROM Account a WHERE a.userId = :userId", Account.class)
    		        .setParameter("userId", userId)
    		        .getResultList();

    	 return result.stream().findFirst(); // 없으면 Optional.empty()
    }

}
