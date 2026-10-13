package com.tcc.faltaoque.exception;

public class PantryInviteNotFoundException extends RuntimeException {

	public PantryInviteNotFoundException(String id) {
		super("Não há um convite de despensa com o id: " + id);
	}
}
