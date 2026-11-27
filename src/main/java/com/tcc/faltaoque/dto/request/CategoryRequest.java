package com.tcc.faltaoque.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Por favor, digite o nome da categoria")
        String name ) {
}
