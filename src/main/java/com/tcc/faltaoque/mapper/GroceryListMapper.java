package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.GroceryListRequest;
import com.tcc.faltaoque.dto.response.GroceryListResponse;
import com.tcc.faltaoque.entity.GroceryList;

public class GroceryListMapper {

    public static GroceryList toEntity(GroceryListRequest request) {
        return GroceryList
                .builder()
                .name(request.name())
                .suggestion(request.suggestion())
                .build();
    }

    public static GroceryListResponse toResponse(GroceryList entity) {
        return GroceryListResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .suggestion(entity.getSuggestion())
                .build();
    }
}
