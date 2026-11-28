package com.tcc.faltaoque.exception;

public class GroceryListNotFoundException extends RuntimeException {

	public GroceryListNotFoundException(String id) {
		super("Não há uma lista de compras com o id: " + id);
	}
}
