package com.tcc.faltaoque.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PantryRequest(
        @Schema(description = "Nome da despensa", example = "Casa")
        @NotBlank @Size(max = 150)
        String title,
        @Size(max = 8, message = "O CEP deve ter no máximo 8 caracteres")
        String cep,
        @Schema(description = "Id da cor da despensa", example = "3")
        int color) {
}
