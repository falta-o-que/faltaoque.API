package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.service.CategoryService;
import com.tcc.faltaoque.dto.request.CategoryRequest;
import com.tcc.faltaoque.dto.response.CategoryResponse;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(
	name = "Category",
	description = "Listar, criar, editar e excluir categorias")
@SecurityRequirement(name = "bearer-key")
public class CategoryController {

	private final CategoryService categoryService;

	@Operation(summary = "Listar todas as categorias")
	@GetMapping
	public ResponseEntity<List<CategoryResponse>> listAllCategories() {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(categoryService.listAllCategories());
	}

	@Operation(summary = "Buscar uma categoria por id")
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable int id) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(categoryService.findCategoryById(id));
	}

	@Operation(summary = "Criar uma nova categoria")
	@PostMapping
	public ResponseEntity<CategoryResponse> createCategory(
	@RequestBody @Valid CategoryRequest categoryRequest) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(categoryService.createCategory(categoryRequest));
	}

	@Operation(summary = "Atualizar uma categoria")
	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponse> updateCategory(
	@PathVariable int id, @RequestBody @Valid CategoryRequest categoryRequest) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(categoryService.updateCategory(id, categoryRequest));
	}

	@Operation(summary = "Deletar uma categoria")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}
}
