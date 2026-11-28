package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.service.UserService;
import com.tcc.faltaoque.dto.request.UserRequest;
import com.tcc.faltaoque.dto.response.UserResponse;
import com.tcc.faltaoque.dto.response.PantryResponse;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(
	name = "User",
	description = "Listar, editar e excluir usuários e listar todas as despensas de um usuário")
@SecurityRequirement(name = "bearer-key")
public class UserController {

	private final UserService userService;

	@Operation(summary = "Listar todos os usuários cadastrados")
	@GetMapping
	public ResponseEntity<List<UserResponse>> listAllUsers() {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(userService.listAllUsers());
	}

	@Operation(summary = "Buscar um usuário por id")
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> findUserById(@PathVariable String id) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(userService.findUserById(id));
	}

	@Operation(summary = "Listar todas as despensas do usuário autenticado")
	@GetMapping("/pantries")
	public ResponseEntity<List<PantryResponse>> listAllPantriesByUser(
	Authentication authentication) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(userService.listAllPantriesByUser(authentication.getName()));
	}

	@Operation(summary = "Atualizar as informações do usuário autenticado")
	@PutMapping
	public ResponseEntity<UserResponse> updateUser(Authentication authentication,
	@RequestBody @Valid UserRequest userRequest) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(userService.updateUser(authentication.getName(), userRequest));
	}

	@Operation(summary = "Deletar o usuário autenticado")
	@DeleteMapping
	public ResponseEntity<Void> deleteUser(Authentication authentication) {
		userService.deleteUser(authentication.getName());
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}
}
