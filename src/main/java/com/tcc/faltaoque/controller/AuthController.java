package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.service.AuthService;
import com.tcc.faltaoque.dto.request.UserRequest;
import com.tcc.faltaoque.dto.response.UserResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@Operation(summary = "Criar um novo usuário")
	@PostMapping("/sign-up")
	public ResponseEntity<UserResponse> createUser(
	@RequestBody @Valid UserRequest userRequest) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(authService.createUser(userRequest));
	}

}
