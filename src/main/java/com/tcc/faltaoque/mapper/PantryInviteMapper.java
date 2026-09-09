package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.response.PantryInviteResponse;
import com.tcc.faltaoque.entity.PantryInvite;

public class PantryInviteMapper {

    public static PantryInviteResponse toResponse(PantryInvite entity) {
        return PantryInviteResponse
                .builder()
                .id(entity.getId())
                .shareInvite(entity.getShareInvite())
                .expiresAt(entity.getExpiresAt())
                .build();
    }
}
