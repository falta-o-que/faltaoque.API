package com.tcc.faltaoque.exception;

public class PantryProductNotFoundException extends RuntimeException {
    public PantryProductNotFoundException(String id) {
        super("Não há produto com o id:" + id);
    }
}
