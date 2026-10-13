package com.tcc.faltaoque.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String id) {
		super("Não há um usuário com o id: " + id);
	}
}
