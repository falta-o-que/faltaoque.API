package com.tcc.faltaoque.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String id) {
        super("Não há uma localização com o id:" + id);
    }
}
