package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.dto.request.PantryProductRequest;
import com.tcc.faltaoque.dto.response.PantryProductDeleteResponse;
import com.tcc.faltaoque.dto.response.PantryProductResponse;
import com.tcc.faltaoque.service.PantryProductService;
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
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(
        name = "Pantry-product",
        description = "Criar, editar, listar e excluir produtos de uma compra.")
@SecurityRequirement(name = "bearer-key")
public class PantryProductController {

    private final PantryProductService pantryProductService;

    @Operation(summary = "Adicionar novo produto")
    @PostMapping
    public ResponseEntity<PantryProductResponse> addProduct (@Valid @RequestBody PantryProductRequest pantryProductRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED).body(pantryProductService.addProduct(pantryProductRequest));
    }

    @Operation(summary = "Editar produto")
    @PutMapping("/{id}")
    public ResponseEntity<PantryProductResponse> editProduct (@PathVariable String id, @Valid @RequestBody PantryProductRequest pantryProductRequest){
        return ResponseEntity.ok(pantryProductService.productUpdate(id, pantryProductRequest));
    }

    @Operation(summary = "Deletar produto")
    @DeleteMapping("{productId}")
    public ResponseEntity<PantryProductDeleteResponse> deletePurchaseProduct(@PathVariable String productId) {
        PantryProductDeleteResponse response =
        pantryProductService.deletePurchaseProduct(productId);

            return ResponseEntity.ok(response);
    }


    @Operation(summary = "Listar produtos de uma compra por categoria")
    @GetMapping("/{purchaseId}/category/{categoryId}")
    public ResponseEntity<List<PantryProductResponse>> listProductsByPantryAndCategory(@PathVariable String purchaseId, @PathVariable int categoryId) {
        return ResponseEntity.ok(
                pantryProductService.listProductsByPurchaseAndCategory(purchaseId, categoryId)
        );
    }

    @Operation(summary = "Buscar produtos de uma compra por nome")
    @GetMapping("/{purchaseId}/name")
    public ResponseEntity<List<PantryProductResponse>> findByName(@PathVariable String purchaseId, @RequestParam String name) {
        return ResponseEntity.ok(
                pantryProductService.filterByName(purchaseId, name)
        );
    }

    @Operation(summary = "Buscar produto por id")
    @GetMapping("/{id}")
    public ResponseEntity<PantryProductResponse> findById(@PathVariable String id){
        return ResponseEntity.ok(
                pantryProductService.findById(id)
        );
    }

    @Operation(summary = "Listar todos os produtos")
    @GetMapping
    public  ResponseEntity<List<PantryProductResponse>> listOfProducts (){
        return ResponseEntity.ok(pantryProductService.listAllProducts());
    }
}
