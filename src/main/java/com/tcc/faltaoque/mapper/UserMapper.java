package com.tcc.faltaoque.mapper;

import com.tcc.faltaoque.dto.request.UserRequest;
import com.tcc.faltaoque.dto.response.UserResponse;
import com.tcc.faltaoque.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toRequest(UserRequest request) {
        return User
                .builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .avatar(request.avatar())
                .build();
    }

    public static UserResponse toEntity(User response) {
        return UserResponse
                .builder()
                .id(response.getId())
                .name(response.getName())
                .email(response.getEmail())
                .avatar(response.getAvatar())
                .role(response.getRole())
                .build();
    }

}
