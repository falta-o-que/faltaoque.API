package com.tcc.faltaoque.dto.request;

import com.tcc.faltaoque.enums.Suggestion;
import java.time.LocalDate;

public record GroceryListRequest(
        String name,
        LocalDate date,
        Suggestion suggestion,
        String location,
        String pantryId) {
}
