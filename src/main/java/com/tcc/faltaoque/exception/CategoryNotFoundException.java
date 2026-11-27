package com.tcc.faltaoque.exception;

public class CategoryNotFoundException extends RuntimeException {

	public CategoryNotFoundException(int id) {
		super("Não há uma categoria com o id: " + id);
	}
}
