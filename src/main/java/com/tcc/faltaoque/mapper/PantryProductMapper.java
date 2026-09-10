package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.PantryProductRequest;
import com.tcc.faltaoque.dto.response.PantryProductResponse;
import com.tcc.faltaoque.entity.PantryProduct;

public class PantryProductMapper {

    public static PantryProduct toRequest(PantryProductRequest request) {
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
                .purchase(request.purchaseId())
                .category(request.categoryId())
                .build();
    }

    public static PantryProductResponse toEntity(PantryProduct response) {
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
                .purchaseId(response.getId())
                .categoryId(response.getId())
                .build();
    }
    
}
