package com.tcc.faltaoque.dto.response;

import lombok.Builder;

@Builder
public record CategoryResponse(
        int id,
        String name ) {
}
