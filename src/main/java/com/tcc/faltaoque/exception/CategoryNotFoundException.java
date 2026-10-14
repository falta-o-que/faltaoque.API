package com.tcc.faltaoque.exception;

public class CategoryNotFoundException extends RuntimeException {

	public CategoryNotFoundException(String id) {
		super("Não há uma categoria com o id: " + id);
	}
}
