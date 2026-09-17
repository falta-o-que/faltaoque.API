package com.tcc.faltaoque.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public record UserLoginRequest(
	@NotBlank(message = "Por favor, digite o seu endereço de e-mail")
	@Size(max = 254)
	@Email(message = "Por favor, digite um endereço de e-mail válido")
	String email,
	@NotBlank(message = "Por favor, digite a senha")
	@Size(min = 8, max = 64, message = "A senha deve ter entre 8 e 64 caracteres")
	String password) {

}
