package com.tcc.faltaoque.controller;

import com.tcc.faltaoque.dto.request.PantryRequest;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.mapper.PantryMapper;
import com.tcc.faltaoque.service.PantryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pantries")
@RequiredArgsConstructor
public class PantryController {

    private final PantryService pantryService;

    //Criar despensa
    @PostMapping
    public ResponseEntity<PantryResponse> createPantry(@Valid @RequestBody PantryRequest pantryRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED).body(pantryService.createPantry(pantryRequest));
    }

    //Listar todas as despensas
    @GetMapping
    public ResponseEntity<List<PantryResponse>> listAllPantries(){
        return ResponseEntity
                .status(HttpStatus.OK).body(pantryService.listAllPantries());
    }

    //Buscar uma despensa pelo id
    @GetMapping("/{id}")
    public ResponseEntity<PantryResponse> findPantryById(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK).body(pantryService.findPantryById(id));
    }

    //Editar os dados de uma despensa
    @PutMapping("/{id}")
    public ResponseEntity<PantryResponse> updatePantry(@PathVariable String id, @Valid @RequestBody PantryRequest pantryRequest){
        return ResponseEntity .status(HttpStatus.OK).body(pantryService.updatePantry(id, pantryRequest));
    }

    //Deletar uma despensa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePantry(@PathVariable String id){
        pantryService.deletePantry(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }

}
