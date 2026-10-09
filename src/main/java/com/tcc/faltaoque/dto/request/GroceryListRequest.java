package com.tcc.faltaoque.dto.request;

import java.time.LocalDate;

public record GroceryListRequest(
        String name,
        LocalDate date,
        Byte suggestion,
        String location,
        String pantryId) {
}
