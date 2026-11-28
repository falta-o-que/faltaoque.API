package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.service.GroceryListService;
import com.tcc.faltaoque.dto.request.GroceryListRequest;
import com.tcc.faltaoque.dto.response.GroceryListResponse;
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
import org.springframework.security.core.Authentication;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/grocery-lists")
@RequiredArgsConstructor
@Tag(
	name = "Grocery List",
	description = "Listar, criar, editar e excluir listas de compras")
@SecurityRequirement(name = "bearer-key")
public class GroceryListController {

	private final GroceryListService groceryListService;

	@Operation(summary = "Listar todas as listas de compras")
	@GetMapping
	public ResponseEntity<List<GroceryListResponse>> listAllGroceryLists() {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(groceryListService.listAllGroceryLists());
	}

	@Operation(summary = "Buscar uma lista de compras por id")
	@GetMapping("/{id}")
	public ResponseEntity<GroceryListResponse> findGroceryListById(@PathVariable String id) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(groceryListService.findGroceryListById(id));
	}

	@Operation(summary = "Criar uma nova lista de compras para o usuário autenticado")
	@PostMapping
	public ResponseEntity<GroceryListResponse> createGroceryList(Authentication authentication,
	@RequestBody @Valid GroceryListRequest groceryListRequest) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(groceryListService.createGroceryList(
				authentication.getName(),
				groceryListRequest));
	}

	@Operation(summary = "Atualizar uma lista de compras do usuário autenticado")
	@PutMapping("/{id}")
	public ResponseEntity<GroceryListResponse> updateGroceryList(Authentication authentication,
	@PathVariable String id, @RequestBody @Valid GroceryListRequest groceryListRequest) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(groceryListService.updateGroceryList(
				authentication.getName(),
				id,
				groceryListRequest));
	}

	@Operation(summary = "Deletar uma lista de compras do usuário autenticado")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGroceryList(Authentication authentication,
	@PathVariable String id) {
		groceryListService.deleteGroceryList(authentication.getName(), id);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

}
