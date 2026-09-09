package com.tcc.faltaoque.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

@Builder
public record PurchaseResponse(
        String id,
        String location,
        LocalDate purchaseDate,
        BigDecimal totalPrice,
        Integer totalProducts,
        LocalDate finishProducts,
        String pantryId
) {
}
