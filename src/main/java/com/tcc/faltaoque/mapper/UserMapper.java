package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.UserRequest;
import com.tcc.faltaoque.dto.response.UserResponse;
import com.tcc.faltaoque.entity.Color;
import com.tcc.faltaoque.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toEntity(UserRequest request, Color avatar) {
        return User
                .builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .avatar(avatar)
                .isActive(request.isActive())
                .build();
    }

    public static UserResponse toResponse(User response, Color avatar) {
        return UserResponse
                .builder()
                .id(response.getId())
                .name(response.getName())
                .email(response.getEmail())
                .avatar(avatar.getId())
                .isActive(response.getIsActive())
                .build();
    }

}
