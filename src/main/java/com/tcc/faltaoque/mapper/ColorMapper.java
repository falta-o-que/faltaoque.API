package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.ColorRequest;
import com.tcc.faltaoque.dto.response.ColorResponse;
import com.tcc.faltaoque.entity.Color;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColorMapper {

    public static Color toEntity(ColorRequest request) {
        return Color
                .builder()
                .name(request.name())
                .hexCode(request.hexCode())
                .build();
    }

    public static ColorResponse toResponse(Color response) {
        return ColorResponse
                .builder()
                .id(response.getId())
                .name(response.getName())
                .hexCode(response.getHexCode())
                .build();
    }

}
