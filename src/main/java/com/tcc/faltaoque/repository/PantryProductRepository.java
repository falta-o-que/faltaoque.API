package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.PantryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PantryProductRepository extends JpaRepository<PantryProduct, String> {

    List<PantryProduct> findByPurchaseId(String purchaseId);

    List<PantryProduct> findByPurchaseIdAndCategoryId(String purchaseId, int categoryId);

    List<PantryProduct> findByPurchaseIdAndName(String purchaseId, String name);

    Optional<PantryProduct> findByIdAndPurchaseId(String id, String purchaseId);

}
