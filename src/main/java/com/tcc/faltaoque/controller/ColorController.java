package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.service.ColorService;
import com.tcc.faltaoque.dto.request.ColorRequest;
import com.tcc.faltaoque.dto.response.ColorResponse;
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
@RequestMapping("/colors")
@RequiredArgsConstructor
@Tag(
	name = "Color",
	description = "Listar, criar, editar e excluir cores")
@SecurityRequirement(name = "bearer-key")
public class ColorController {

	private final ColorService colorService;

	@Operation(summary = "Listar todas as cores")
	@GetMapping
	public ResponseEntity<List<ColorResponse>> listAllColors() {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(colorService.listAllColors());
	}

	@Operation(summary = "Buscar uma cor por id")
	@GetMapping("/{id}")
	public ResponseEntity<ColorResponse> findColorById(@PathVariable int id) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(colorService.findColorById(id));
	}

	@Operation(summary = "Adicionar uma cor")
	@PostMapping
	public ResponseEntity<ColorResponse> addColor(
	@RequestBody @Valid ColorRequest colorRequest) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(colorService.addColor(colorRequest));
	}

	@Operation(summary = "Atualizar uma cor")
	@PutMapping("/{id}")
	public ResponseEntity<ColorResponse> updateColor(
	@PathVariable int id, @RequestBody @Valid ColorRequest colorRequest) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(colorService.updateColor(id, colorRequest));
	}

	@Operation(summary = "Deletar uma cor")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteColor(@PathVariable int id) {
		colorService.deleteColor(id);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}
}
