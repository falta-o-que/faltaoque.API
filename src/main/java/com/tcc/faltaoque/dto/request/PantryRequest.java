package com.tcc.faltaoque.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PantryRequest(
        @NotBlank @Size(max = 150)
        String title,
        @Size(max = 8, message = "O CEP deve ter no máximo 8 caracteres")
        String location,
        Byte color) {
}
