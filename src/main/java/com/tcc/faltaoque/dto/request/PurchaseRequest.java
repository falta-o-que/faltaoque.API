package com.tcc.faltaoque.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PurchaseRequest(
        @NotBlank(message = "Por favor, digite um título para a compra")
        @Size(max = 100, message = "O nome deve ter até 100 caracteres")
        String title,
        @NotBlank(message = "A compra deve estar associada à um local")
        @Size(max = 36, message = "O UUID deve possuir 36 caracteres")
        String location,
        LocalDate purchaseDate,
        boolean isFinished,
        LocalDate finishDate,
        String qrCodeId,
        @NotBlank(message = "A compra deve estar associada à uma despensa")
        @Size(max = 36, message = "O UUID deve possuir 36 caracteres")
        String pantryId
) {
}
