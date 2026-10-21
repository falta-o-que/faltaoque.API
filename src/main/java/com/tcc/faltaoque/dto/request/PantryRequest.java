package com.tcc.faltaoque.dto.request;

public record PantryRequest(
        String title,
        String cep,
        int color,
        String shareInvite) {
}
