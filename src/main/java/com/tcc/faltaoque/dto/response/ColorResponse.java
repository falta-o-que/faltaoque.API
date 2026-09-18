package com.tcc.faltaoque.dto.response;

import lombok.Builder;

@Builder
public record ColorResponse(
        int id,
        String name,
        String hexCode
) {
}
