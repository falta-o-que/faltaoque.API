package com.tcc.faltaoque.dto.response;

import lombok.Builder;

@Builder
public record PantryResponse(
        String id,
        String title,
        String location,
        Byte color) {
}
