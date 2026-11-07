package com.tcc.faltaoque.dto.request;

import com.tcc.faltaoque.enums.UnitOfMeasure;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PantryProductRequest(
        String name,
        int quantity,
        Double contentValue,
        UnitOfMeasure unitOfMeasure,
        BigDecimal price,
        String brand,
        LocalDate expirationDate,
        String purchase,
        int category
) {
}
