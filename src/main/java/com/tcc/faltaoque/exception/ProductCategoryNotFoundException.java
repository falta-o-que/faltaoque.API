package com.tcc.faltaoque.exception;

public class ProductCategoryNotFoundException extends RuntimeException {
    public ProductCategoryNotFoundException(int id) {
        super("Não existe nenhum produto com a categoria" + id);
    }
}
