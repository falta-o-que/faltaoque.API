package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.PantryRequest;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.entity.Color;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.entity.PantryInvite;

public class PantryMapper {

    public static Pantry toEntity(PantryRequest request, Color color, PantryInvite invite) {
        return Pantry
                .builder()
                .title(request.title())
                .location(request.location())
                .colorId(color)
                .shareInvite(invite)
                .build();
    }

    public static PantryResponse toResponse(Pantry entity, Color color, PantryInvite invite) {
        return PantryResponse
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .location(entity.getLocation())
                .colorId(color.getId())
                .shareInvite(invite.getId())
                .build();
    }
}
