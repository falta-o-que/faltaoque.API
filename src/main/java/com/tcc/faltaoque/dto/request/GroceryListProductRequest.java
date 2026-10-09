package com.tcc.faltaoque.dto.request;

public record GroceryListProductRequest(
        String name,
        int quantity,
        Double contentValue,
        Byte unitOfMeasure,
        String groceryList,
        int category
) {
}
