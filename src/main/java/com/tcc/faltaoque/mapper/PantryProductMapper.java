package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.PantryProductRequest;
import com.tcc.faltaoque.dto.response.PantryProductResponse;
import com.tcc.faltaoque.entity.Category;
import com.tcc.faltaoque.entity.PantryProduct;
import com.tcc.faltaoque.entity.Purchase;

public class PantryProductMapper {

    public static PantryProduct toRequest(PantryProductRequest request, Purchase purchase, Category category) {
        return PantryProduct
                .builder()
                .name(request.name())
                .quantity(request.quantity())
                .isInPantry(request.isInPantry())
                .weight(request.weight())
                .price(request.price())
                .brand(request.brand())
                .expirationDate(request.expirationDate())
                .missingDate(request.missingDate())
                .purchaseId(purchase)
                .categoryId(category)
                .build();
    }

    public static PantryProductResponse toEntity(PantryProduct response, Purchase purchase, Category category) {
        return PantryProductResponse
                .builder()
                .id(response.getId())
                .name(response.getName())
                .quantity(response.getQuantity())
                .isInPantry(response.getIsInPantry())
                .weight(response.getWeight())
                .price(response.getPrice())
                .brand(response.getBrand())
                .expirationDate(response.getExpirationDate())
                .missingDate(response.getMissingDate())
                .purchaseId(purchase.getId())
                .categoryId(category.getId())
                .build();
    }
    
}
