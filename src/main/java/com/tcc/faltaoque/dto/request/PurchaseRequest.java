package com.tcc.faltaoque.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseRequest(
        String title,
        String location,
        LocalDate purchaseDate,
        boolean isFinished,
        LocalDate finishDate,
        String qrCodeId,
        String pantryId
) {
}
