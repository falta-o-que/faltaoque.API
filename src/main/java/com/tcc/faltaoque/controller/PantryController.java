package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.dto.request.PantryRequest;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.service.PantryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pantries")
@RequiredArgsConstructor
public class PantryController {

    private final PantryService pantryService;

    @Operation(summary = "Criar uma despensa para o usuário autenticado")
    @PostMapping
    public ResponseEntity<PantryResponse> createPantry(@Valid @RequestBody PantryRequest pantryRequest, Authentication authentication){
        return ResponseEntity
                .status(HttpStatus.CREATED).body(pantryService.createPantry(pantryRequest, authentication.getName()));
    }

    @Operation(summary = "Listar todas as despensas (administrador)")
    @GetMapping
    public ResponseEntity<List<PantryResponse>> listAllPantries(){
        return ResponseEntity
                .status(HttpStatus.OK).body(pantryService.listAllPantries());
    }

    @Operation(summary = "Buscar uma despensa do usuário autenticado")
    @GetMapping("/{id}")
    public ResponseEntity<PantryResponse> findPantryById(@PathVariable String id, Authentication authentication){
        return ResponseEntity
                .status(HttpStatus.OK).body(pantryService.findPantryById(id, authentication.getName()));
    }

    @Operation(summary = "Atualizar uma despensa do usuário autenticado")
    @PutMapping("/{id}")
    public ResponseEntity<PantryResponse> updatePantry(   @PathVariable String id, @Valid @RequestBody PantryRequest pantryRequest, Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pantryService.updatePantry(id, pantryRequest, authentication.getName()));
    }

    @Operation(summary = "Excluir uma despensa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePantry(@PathVariable String id, Authentication authentication){
        pantryService.deletePantry(id, authentication.getName());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
