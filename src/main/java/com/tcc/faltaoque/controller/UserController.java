package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.service.UserService;
import com.tcc.faltaoque.dto.response.UserResponse;
import com.tcc.faltaoque.dto.response.PantryResponse;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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

}
