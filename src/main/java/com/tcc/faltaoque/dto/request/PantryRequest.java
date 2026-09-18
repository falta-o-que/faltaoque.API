package com.tcc.faltaoque.dto.request;

public record PantryRequest(
        String title,
        String location,
        int colorId,
        String shareInvite) {
}
