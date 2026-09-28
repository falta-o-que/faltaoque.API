package com.tcc.faltaoque.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseRequest(
        String title,
        String location,
        LocalDate purchaseDate,
        BigDecimal totalPrice,
        int totalProducts,
        LocalDate missingProducts,
        String pantryId
) {
}
