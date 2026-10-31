package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.dto.request.PurchaseRequest;
import com.tcc.faltaoque.dto.response.PurchaseResponse;
import com.tcc.faltaoque.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
@Tag(
        name = "Purchase",
        description = "Criar, editar, listar e excluir compras de uma despensa.")
@SecurityRequirement(name = "bearer-key")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Operation(summary = "Criar nova compra")
    @PostMapping
    public ResponseEntity<PurchaseResponse> createPurchase (@Valid @RequestBody PurchaseRequest purchaseRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED).body(purchaseService.createPurchase(purchaseRequest));
    }

    @Operation(summary = "Editar compra")
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseResponse> editPurchase (@PathVariable String id, @RequestBody PurchaseRequest purchaseRequest ){
        PurchaseResponse purchase = purchaseService.purchaseUpdate(id, purchaseRequest);

        return ResponseEntity.ok(purchase);
    }

    @Operation(summary = "Deletar compra")
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deletePurchase (@PathVariable String id){
        purchaseService.deleteById(id);
                return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar por id")
    @GetMapping("/{id}")
    public  ResponseEntity<PurchaseResponse> purchaseById (@PathVariable String id){
       PurchaseResponse purchase = purchaseService.findById(id);
        return ResponseEntity.ok(purchase);
    }

    @Operation(summary = "Listar todas as compras")
    @GetMapping
    public  ResponseEntity<List<PurchaseResponse>> listOfPurchases (){
        return ResponseEntity.ok(purchaseService.listAllPurchases());
    }

    @Operation(summary = "Listar compras de uma despensa com seus produtos")
    @GetMapping("/pantry/{pantryId}")
    public ResponseEntity<List<PurchaseResponse>> listProductsByPurchase(
            @PathVariable String pantryId) {

        return ResponseEntity.ok(
                purchaseService.listProductsByPurchase(pantryId)
        );
    }

}
