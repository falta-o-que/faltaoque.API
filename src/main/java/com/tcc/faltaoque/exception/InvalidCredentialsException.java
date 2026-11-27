package com.tcc.faltaoque.exception;

public class InvalidCredentialsException extends RuntimeException {

	public InvalidCredentialsException() {
		super("Credenciais inválidas!");
	}
}
