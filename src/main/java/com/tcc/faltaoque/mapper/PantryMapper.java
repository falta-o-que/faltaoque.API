package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.PantryRequest;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.entity.Color;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.entity.PantryInvite;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PantryMapper {

    public static Pantry toEntity(PantryRequest request, Color color) {
        return Pantry
                .builder()
                .title(request.title())
                .cep(request.cep())
                .color(color)
                .build();
    }

    public static PantryResponse toResponse(Pantry entity, Color color, PantryInvite invite) {
        return PantryResponse
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .cep(entity.getCep())
                .color(color.getId())
                .shareInvite(invite == null ? null : invite.getId())
                .build();
    }
}
