package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.service.UserService;
import com.tcc.faltaoque.dto.request.UserRequest;
import com.tcc.faltaoque.dto.response.UserResponse;
import com.tcc.faltaoque.dto.response.PantryResponse;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
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

	@Operation(summary = "Listar todas as despensas de um único usuário")
	@GetMapping("{id}/pantries")
	public ResponseEntity<List<PantryResponse>> listAllPantriesByUser(
	@PathVariable String id) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(userService.listAllPantriesByUser(id));
	}

	@Operation(summary = "Criar um novo usuário")
	@PostMapping
	public ResponseEntity<UserResponse> createUser(
	@RequestBody @Valid UserRequest userRequest) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(userService.createUser(userRequest));
	}

	@Operation(summary = "Atualizar as informações de um usuário")
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id,
	@RequestBody @Valid UserRequest userRequest) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(userService.updateUser(id, userRequest));
	}

	@Operation(summary = "Deletar um usuário")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}
}
