package com.tcc.faltaoque.dto.response;

import lombok.Builder;

@Builder
public record GroceryListResponse(
        String id,
        String name,
        Byte suggestion ) {
}
