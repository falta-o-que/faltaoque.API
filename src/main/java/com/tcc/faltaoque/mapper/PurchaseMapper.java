package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.PurchaseRequest;
import com.tcc.faltaoque.dto.response.PurchaseResponse;
import com.tcc.faltaoque.entity.Purchase;

public class PurchaseMapper {

    public static Purchase toEntity(PurchaseRequest request) {
        return Purchase
                .builder()
                .title(request.title())
                .location(request.location())
                .purchaseDate(request.purchaseDate())
                .totalPrice(request.totalPrice())
                .totalProducts(request.totalProducts())
                .missingProducts(request.missingProducts())
                .build();
    }

    public static PurchaseResponse toResponse(Purchase entity) {
        return PurchaseResponse
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .location(entity.getLocation())
                .purchaseDate(entity.getPurchaseDate())
                .totalPrice(entity.getTotalPrice())
                .totalProducts(entity.getTotalProducts())
                .missingProducts(entity.getMissingProducts())
                .build();
    }
}
