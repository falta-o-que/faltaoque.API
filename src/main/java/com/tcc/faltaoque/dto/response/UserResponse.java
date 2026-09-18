package com.tcc.faltaoque.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(
        String id,
        String name,
        String email,
        int avatarId,
        int role,
        boolean isActive
) {
}
