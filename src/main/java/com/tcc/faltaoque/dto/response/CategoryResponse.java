package com.tcc.faltaoque.dto.response;

import lombok.Builder;

@Builder
public record CategoryResponse(
        String id,
        String name ) {
}
