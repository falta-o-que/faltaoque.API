package com.tcc.faltaoque.dto.response;

import com.tcc.faltaoque.enums.Role;
import lombok.Builder;

@Builder
public record UserResponse(
        String id,
        String name,
        String email,
        int avatar,
        Role role,
        boolean isActive
) {
}
