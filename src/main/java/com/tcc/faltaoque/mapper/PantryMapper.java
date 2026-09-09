package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.PantryRequest;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.entity.Pantry;

public class PantryMapper {

    public static Pantry toRequest(PantryRequest request) {
        return Pantry
                .builder()
                .title(request.title())
                .location(request.location())
                .color(request.color())
                .build();
    }

    public static PantryResponse toEntity(Pantry entity) {
        return PantryResponse
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .location(entity.getLocation())
                .color(entity.getColor())
                .build();
    }
}
