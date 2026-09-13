package com.tcc.faltaoque.dto.response;

import com.tcc.faltaoque.enums.Avatar;
import com.tcc.faltaoque.enums.Role;
import lombok.Builder;

@Builder
public record UserResponse(
        String id,
        String name,
        String email,
        Avatar avatar,
        Role role
) {
}
