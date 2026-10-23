package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.dto.request.PantryInviteRequest;
import com.tcc.faltaoque.dto.response.PantryInviteResponse;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.service.PantryInviteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pantries-invites")
@RequiredArgsConstructor
public class PantryInviteController {

    private final PantryInviteService pantryInviteService;

    @Operation(summary = "Gerar o link de convite (válido por 12 horas) de uma despensa")
    @PostMapping
    public ResponseEntity<PantryInviteResponse> createInvite(
            @Valid @RequestBody PantryInviteRequest pantryInviteRequest, Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pantryInviteService.createInvite(pantryInviteRequest, authentication.getName()));
    }

    @Operation(summary = "Listar todos os convites (administrador)")
    @GetMapping
    public ResponseEntity<List<PantryInviteResponse>> listAllInvites() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pantryInviteService.listAllInvites());
    }

    @Operation(summary = "Buscar um convite pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<PantryInviteResponse> findInviteById(
            @PathVariable String id, Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pantryInviteService.findInviteById(id, authentication.getName()));
    }

    @Operation(summary = "Excluir um convite")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvite(
            @PathVariable String id, Authentication authentication) {
        pantryInviteService.deleteInvite(id, authentication.getName());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Entrar em uma despensa pelo link de convite")
    @PostMapping("/join/{shareInvite}")
    public ResponseEntity<PantryResponse> joinPantry(
            @PathVariable String shareInvite, Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pantryInviteService.joinPantry(shareInvite, authentication.getName()));
    }
}
