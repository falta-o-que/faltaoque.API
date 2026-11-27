package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String> {

    Optional<Purchase> findByQrCodeId(String qrCodeId);
}
