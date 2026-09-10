package com.tcc.faltaoque.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PantryInviteResponse(
     String id,
     String shareInvite,
     LocalDateTime expiresAt
) {
}
