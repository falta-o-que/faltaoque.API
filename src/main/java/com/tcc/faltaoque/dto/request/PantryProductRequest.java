package com.tcc.faltaoque.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PantryProductRequest(
        String name,
        int quantity,
        Boolean isInPantry,
        Double weight,
        BigDecimal price,
        String brand,
        LocalDate expirationDate,
        LocalDate missingDate,
        String purchaseId,
        int categoryId
) {
}
