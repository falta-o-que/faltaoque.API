package com.tcc.faltaoque.dto.request;

import com.tcc.faltaoque.enums.Avatar;

public record UserRequest(
        String name,
        String email,
        String password,
        Avatar avatar

) {
}
