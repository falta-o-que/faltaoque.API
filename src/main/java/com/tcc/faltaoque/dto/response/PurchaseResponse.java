package com.tcc.faltaoque.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

@Builder
public record PurchaseResponse(
        String id,
        String title,
        String location,
        LocalDate purchaseDate,
        BigDecimal totalPrice,
        int totalProducts,
        boolean isFinished,
        LocalDate finishDate,
        String qrCodeId,
        String pantryId
) {
}
