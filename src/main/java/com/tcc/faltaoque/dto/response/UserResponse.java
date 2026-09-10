package com.tcc.faltaoque.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(
        String id,
        String name,
        String email,
        Byte avatar,
        Byte role
) {
}
