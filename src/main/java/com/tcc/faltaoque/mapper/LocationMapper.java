package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.LocationRequest;
import com.tcc.faltaoque.dto.response.LocationResponse;
import com.tcc.faltaoque.entity.Location;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LocationMapper {

    public static Location toEntity(LocationRequest request) {
        return Location
                .builder()
                .localName(request.localName())
                .customName(request.customName())
                .cep(request.cep())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .build();
    }

    public static LocationResponse toResponse(Location entity) {
        return LocationResponse
                .builder()
                .id(entity.getId())
                .localName(entity.getLocalName())
                .customName(entity.getCustomName())
                .cep(entity.getCep())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }
}
