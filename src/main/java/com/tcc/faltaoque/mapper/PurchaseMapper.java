package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.PurchaseRequest;
import com.tcc.faltaoque.dto.response.PurchaseResponse;
import com.tcc.faltaoque.entity.Purchase;

public class PurchaseMapper {

    public static Purchase toRequest(PurchaseRequest request) {
        return Purchase
                .builder()
                .location(request.location())
                .purchaseDate(request.purchaseDate())
                .totalPrice(request.totalPrice())
                .totalProducts(request.totalProducts())
                .finishProducts(request.finishProducts())
                .build();
    }

    public static PurchaseResponse toEntity(Purchase entity) {
        return PurchaseResponse
                .builder()
                .id(entity.getId())
                .location(entity.getLocation())
                .purchaseDate(entity.getPurchaseDate())
                .totalPrice(entity.getTotalPrice())
                .totalProducts(entity.getTotalProducts())
                .finishProducts(entity.getFinishProducts())
                .build();
    }
}
