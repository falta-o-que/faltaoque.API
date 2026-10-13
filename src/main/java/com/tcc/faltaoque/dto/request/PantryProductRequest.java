package com.tcc.faltaoque.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PantryProductRequest(
        String name,
        int quantity,
        Double contentValue,
        Byte unitOfMeasure,
        BigDecimal price,
        String brand,
        LocalDate expirationDate,
        String purchase,
        int category
) {
}
