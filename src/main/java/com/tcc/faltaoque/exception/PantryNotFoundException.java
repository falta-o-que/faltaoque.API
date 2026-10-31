package com.tcc.faltaoque.exception;

public class PantryNotFoundException extends RuntimeException {
    public PantryNotFoundException(String id) {
        super("Não há despensa com o id:" + id);
    }
}
