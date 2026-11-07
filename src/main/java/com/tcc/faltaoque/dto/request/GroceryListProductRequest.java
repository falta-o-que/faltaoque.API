package com.tcc.faltaoque.dto.request;

import com.tcc.faltaoque.enums.UnitOfMeasure;

public record GroceryListProductRequest(
        String name,
        int quantity,
        Double contentValue,
        UnitOfMeasure unitOfMeasure,
        String groceryList,
        int category
) {

}
