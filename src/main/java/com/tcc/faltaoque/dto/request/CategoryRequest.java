package com.tcc.faltaoque.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
		@NotBlank(message = "Por favor, digite o nome da categoria")
		@Size(max = 50)
		String name ) {
}
