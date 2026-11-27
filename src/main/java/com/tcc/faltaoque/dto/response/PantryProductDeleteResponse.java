package com.tcc.faltaoque.dto.response;

import lombok.Builder;

@Builder
public record PantryProductDeleteResponse(
        boolean isDeleted
) {
}
