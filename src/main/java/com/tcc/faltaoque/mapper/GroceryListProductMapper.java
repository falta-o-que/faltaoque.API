package com.tcc.faltaoque.mapper;


import com.tcc.faltaoque.dto.request.GroceryListProductRequest;
import com.tcc.faltaoque.dto.response.GroceryListProductResponse;
import com.tcc.faltaoque.entity.Category;
import com.tcc.faltaoque.entity.GroceryList;
import com.tcc.faltaoque.entity.GroceryListProduct;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GroceryListProductMapper {

    public static GroceryListProduct toEntity(GroceryListProductRequest request, GroceryList groceryList, Category category) {
        return GroceryListProduct
                .builder()
                .name(request.name())
                .quantity(request.quantity())
                .contentValue(request.contentValue())
                .unitOfMeasure(request.unitOfMeasure())
                .groceryList(groceryList)
                .category(category)
                .build();
    }

    public static GroceryListProductResponse toResponse(GroceryListProduct response, GroceryList groceryList, Category category) {
        return GroceryListProductResponse
                .builder()
                .id(response.getId())
                .name(response.getName())
                .quantity(response.getQuantity())
                .contentValue(response.getContentValue())
                .unitOfMeasure(response.getUnitOfMeasure())
                .isTaken(response.isTaken())
                .groceryList(groceryList.getId())
                .category(category.getId())
                .build();
    }

}
