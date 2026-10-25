package com.tcc.faltaoque.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record GroceryListResponse(
        String id,
        String name,
        Byte suggestion,
        LocalDate date,
        String location,
        BigDecimal estimatedPrice,
        String pantry,
        boolean isActive
) {
}
