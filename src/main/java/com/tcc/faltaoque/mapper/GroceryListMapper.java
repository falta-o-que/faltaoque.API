package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.GroceryListRequest;
import com.tcc.faltaoque.dto.response.GroceryListResponse;
import com.tcc.faltaoque.entity.GroceryList;
import com.tcc.faltaoque.entity.Pantry;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GroceryListMapper {

    public static GroceryList toEntity(GroceryListRequest request, Pantry pantry) {
        return GroceryList
                .builder()
                .name(request.name())
                .date(request.date())
                .location(request.location())
                .suggestion(request.suggestion())
                .pantry(pantry)
                .build();
    }

    public static GroceryListResponse toResponse(GroceryList entity, Pantry pantry) {
        return GroceryListResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .suggestion(entity.getSuggestion())
                .date(entity.getDate())
                .location(entity.getLocation())
                .estimatedPrice(entity.getEstimatedPrice())
                .pantry(pantry.getId())
                .isActive(entity.isActive())
                .build();
    }
}
