package com.tcc.faltaoque.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record PantryProductResponse(
        String id,
        String name,
        Byte quantity,
        Boolean isInPantry,
        Double weight,
        BigDecimal price,
        String brand,
        LocalDate expirationDate,
        LocalDate missingDate,
        String purchaseId,
        String categoryId
) {
}
