package com.tcc.faltaoque.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseRequest(
        String location,
        LocalDate purchaseDate,
        BigDecimal totalPrice,
        Integer totalProducts,
        LocalDate finishProducts,
        String pantryId
) {
}
