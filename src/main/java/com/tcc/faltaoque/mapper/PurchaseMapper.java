package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.PurchaseRequest;
import com.tcc.faltaoque.dto.response.PurchaseResponse;
import com.tcc.faltaoque.entity.Location;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.entity.Purchase;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PurchaseMapper {

    public static Purchase toEntity(PurchaseRequest request,Location location, Pantry pantry) {
        return Purchase
                .builder()
                .title(request.title())
                .location(location)
                .purchaseDate(request.purchaseDate())
                .qrCodeId(request.qrCodeId())
                .pantry(pantry)
                .build();
    }

    public static PurchaseResponse toResponse(Purchase entity, Location location, Pantry pantry) {
        return PurchaseResponse
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .location(location.getId())
                .purchaseDate(entity.getPurchaseDate())
                .totalPrice(entity.getTotalPrice())
                .totalProducts(entity.getTotalProducts())
                .isFinished(entity.isFinished())
                .finishDate(entity.getFinishDate())
                .qrCodeId(entity.getQrCodeId())
                .pantryId(pantry.getId())
                .build();
    }
}
