package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.PantryProductRequest;
import com.tcc.faltaoque.dto.response.PantryProductResponse;
import com.tcc.faltaoque.entity.Category;
import com.tcc.faltaoque.entity.PantryProduct;
import com.tcc.faltaoque.entity.Purchase;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PantryProductMapper {

    public static PantryProduct toRequest(PantryProductRequest request, Purchase purchase, Category category) {
        return PantryProduct
                .builder()
                .name(request.name())
                .quantity(request.quantity())
                .currentQuantity(request.quantity())
                .contentValue(request.contentValue())
                .unitOfMeasure(request.unitOfMeasure())
                .price(request.price())
                .brand(request.brand())
                .expirationDate(request.expirationDate())
                .purchase(purchase)
                .category(category)
                .build();
    }

    public static PantryProductResponse toEntity(PantryProduct response, Purchase purchase, Category category) {
        return PantryProductResponse
                .builder()
                .id(response.getId())
                .name(response.getName())
                .quantity(response.getQuantity())
                .currentQuantity(response.getCurrentQuantity())
                .contentValue(response.getContentValue())
                .unitOfMeasure(response.getUnitOfMeasure())
                .finishDate(response.getFinishDate())
                .isDeleted(response.isDeleted())
                .price(response.getPrice())
                .brand(response.getBrand())
                .expirationDate(response.getExpirationDate())
                .purchase(purchase.getId())
                .category(category.getId())
                .build();
    }

}
