package com.tcc.faltaoque.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ColorRequest(
		@NotBlank(message = "Por favor, digite um nome para a cor")
		@Size(max = 50)
        String name,
		@NotBlank(message = "Por favor, digite o código hexadecimal da cor")
		@Size(max = 7)
        String hexCode
) {
}
