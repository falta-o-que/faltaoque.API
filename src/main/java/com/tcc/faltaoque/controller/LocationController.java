package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.service.LocationService;
import com.tcc.faltaoque.dto.request.LocationRequest;
import com.tcc.faltaoque.dto.response.LocationResponse;
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
@RequestMapping("/locations")
@RequiredArgsConstructor
@Tag(
	name = "Location",
	description = "Listar, criar, editar e excluir localizações")
@SecurityRequirement(name = "bearer-key")
public class LocationController {

	private final LocationService locationService;

	@Operation(summary = "Listar todas as localizações")
	@GetMapping
	public ResponseEntity<List<LocationResponse>> listAllLocations() {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(locationService.listAllLocations());
	}

	@Operation(summary = "Buscar uma localização por id")
	@GetMapping("/{id}")
	public ResponseEntity<LocationResponse> findLocationById(@PathVariable String id) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(locationService.findLocationById(id));
	}

	@Operation(summary = "Criar uma localização")
	@PostMapping
	public ResponseEntity<LocationResponse> createLocation(
	@RequestBody @Valid LocationRequest locationRequest) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(locationService.createLocation(locationRequest));
	}

	@Operation(summary = "Atualizar uma localização")
	@PutMapping("/{id}")
	public ResponseEntity<LocationResponse> updateLocation(
	@PathVariable String id, @RequestBody @Valid LocationRequest locationRequest) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(locationService.updateLocation(id, locationRequest));
	}

	@Operation(summary = "Deletar uma localização")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLocation(@PathVariable String id) {
		locationService.deleteLocation(id);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}
}
