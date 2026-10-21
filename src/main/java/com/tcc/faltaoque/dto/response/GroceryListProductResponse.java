package com.tcc.faltaoque.dto.response;

import lombok.Builder;

@Builder
public record GroceryListProductResponse(
        String id,
        String name,
        int quantity,
        Double contentValue,
        Byte unitOfMeasure,
        boolean isTaken,
        String groceryList,
        int category
) {
}
