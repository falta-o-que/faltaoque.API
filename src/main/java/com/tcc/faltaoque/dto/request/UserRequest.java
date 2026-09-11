package com.tcc.faltaoque.dto.request;

public record UserRequest(
        String name,
        String email,
        String password,
        Byte avatar

) {
}
