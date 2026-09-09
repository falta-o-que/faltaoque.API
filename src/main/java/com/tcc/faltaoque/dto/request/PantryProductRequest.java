package com.tcc.faltaoque.dto.request;

import com.tcc.faltaoque.entity.Category;
import com.tcc.faltaoque.entity.Purchase;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PantryProductRequest(
        String name,
        Byte quantity,
        Boolean isInPantry,
        Double weight,
        BigDecimal price,
        String brand,
        LocalDate expirationDate,
        LocalDate missingDate,
        Purchase purchaseId,
        Category categoryId
) {
}
