package com.tcc.faltaoque.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record PantryProductResponse(
        String id,
        String name,
        int quantity,
        int currentQuantity,
        Boolean isInPantry,
        Double contentValue,
        Byte unitOfMeasure,
        BigDecimal price,
        String brand,
        LocalDate expirationDate,
        LocalDate finishDate,
        boolean isDeleted,
        String purchase,
        int category
) {
}
