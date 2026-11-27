package com.tcc.faltaoque.exception;

public class AlreadyExistingPurchaseException extends RuntimeException {

    public AlreadyExistingPurchaseException(String id) {
        super("Já existe uma compra cadastrada com este QR Code: " + id);
    }
}
