package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.GroceryListRequest;
import com.tcc.faltaoque.dto.response.GroceryListResponse;
import com.tcc.faltaoque.entity.GroceryList;
import com.tcc.faltaoque.entity.Location;
import com.tcc.faltaoque.entity.Pantry;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GroceryListMapper {

    public static GroceryList toEntity(GroceryListRequest request, Location location, Pantry pantry) {
        return GroceryList
                .builder()
                .name(request.name())
                .date(request.date())
                .location(location)
                .suggestion(request.suggestion())
                .pantry(pantry)
                .build();
    }

    public static GroceryListResponse toResponse(GroceryList entity,Location location, Pantry pantry) {
        return GroceryListResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .suggestion(entity.getSuggestion())
                .date(entity.getDate())
                .location(location.getId())
                .estimatedPrice(entity.getEstimatedPrice())
                .pantry(pantry.getId())
                .isActive(entity.isActive())
                .build();
    }
}
