package com.tcc.faltaoque.exception;

public class ExistingEmailException extends RuntimeException{

	public ExistingEmailException(String email) {
		super("O email " + email + " já está em uso!");
	}
}
