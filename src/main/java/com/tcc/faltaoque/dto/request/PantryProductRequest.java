package com.tcc.faltaoque.dto.request;

import com.tcc.faltaoque.enums.UnitOfMeasure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PantryProductRequest(
        @NotBlank(message = "Por favor, digite um nome para o produto")
        @Size(max = 100, message = "O nome deve ter até 100 caracteres")
        String name,
        int quantity,
        Double contentValue,
        UnitOfMeasure unitOfMeasure,
        BigDecimal price,
        @Size(max = 100, message = "A marca deve ter até 100 caracteres")
        String brand,
        LocalDate expirationDate,
        @NotBlank(message = "O produto deve estar relacionado à uma compra")
        @Size(max = 36, message = "O UUID deve possuir 36 caracteres")
        String purchase,
        @NotNull(message = "O produto deve pertencer à uma categoria")
        int category
) {
}
