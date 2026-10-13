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

    public static UserResponse toResponse(User entity, Color avatar) {
        return UserResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .avatar(avatar.getId())
                .role(entity.getRole())
                .isActive(entity.getIsActive())
                .build();
    }

}
