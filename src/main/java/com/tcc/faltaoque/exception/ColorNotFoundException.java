package com.tcc.faltaoque.exception;

public class ColorNotFoundException extends RuntimeException {

	public ColorNotFoundException(int id) {
		super("Não há uma cor com o id: " + id);
	}
}
