package com.tcc.faltaoque.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record LocationRequest (
		@NotBlank(message = "Por favor, digite o nome do local")
		String localName,
		String customName,
		@NotBlank(message = "Por favor, digite o CEP do local")
		String cep,
		@NotNull(message = "Por favor, digite a latitude do local")
		BigDecimal latitude,
		@NotNull(message = "Por favor, digite a longitude do local")
		BigDecimal longitude
) {
}
