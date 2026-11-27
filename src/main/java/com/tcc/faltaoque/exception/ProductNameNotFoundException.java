package com.tcc.faltaoque.exception;

public class ProductNameNotFoundException extends RuntimeException {
    public ProductNameNotFoundException(String name) {
        super("Não existe nenhum produto com o nome" + name);
    }
}
