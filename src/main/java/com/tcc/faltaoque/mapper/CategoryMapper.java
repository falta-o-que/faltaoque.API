package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.CategoryRequest;
import com.tcc.faltaoque.dto.response.CategoryResponse;
import com.tcc.faltaoque.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toRequest(CategoryRequest request) {
        return Category
                .builder()
                .name(request.name())
                .build();
    }

    public static CategoryResponse toEntity(Category response) {
        return CategoryResponse
                .builder()
                .id(response.getId())
                .name(response.getName())
                .build();
    }


}
