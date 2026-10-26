package com.tcc.faltaoque.dto.response;

import com.tcc.faltaoque.enums.Suggestion;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record GroceryListResponse(
        String id,
        String name,
        Suggestion suggestion,
        LocalDate date,
        String location,
        BigDecimal estimatedPrice,
        String pantry,
        boolean isActive
) {

}
