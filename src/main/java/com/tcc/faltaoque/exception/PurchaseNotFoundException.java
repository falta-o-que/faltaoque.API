package com.tcc.faltaoque.exception;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException(String id) {
        super("Não há uma compra com o id:" + id);
    }
}
