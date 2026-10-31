package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String> {

    List<Purchase> findByPantryId (String purchaseId);
}
