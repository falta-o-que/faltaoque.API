package com.tcc.faltaoque.service;

import com.tcc.faltaoque.dto.request.PantryRequest;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.mapper.PantryMapper;
import com.tcc.faltaoque.repository.PantryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PantryService {

    private final PantryRepository pantryRepository;

    //Criar despensa
    public PantryResponse createPantry(PantryRequest pantryRequest){
        Pantry pantry = PantryMapper.toRequest(pantryRequest);
        Pantry savedPantry = pantryRepository.save(pantry);
        return PantryMapper.toEntity(savedPantry);
    }

    //Listar todas as despensas
    public List<PantryResponse> listAllPantries(){
        return pantryRepository.findAll()
                .stream()
                .map(PantryMapper::toEntity)
                .collect(Collectors.toList());

    }

    //Buscar por id
    public PantryResponse findPantryById(String id){
        Pantry pantry = findPantryOrThrow(id);
        return PantryMapper.toEntity(pantry);
    }

    //Editar despensa
    public PantryResponse updatePantry(String id, PantryRequest pantryRequest){
        Pantry pantry = findPantryOrThrow(id);
        pantry.setTitle(pantryRequest.title());
        pantry.setLocation(pantryRequest.location());
        pantry.setColor(pantryRequest.color());

        Pantry updatedPantry = pantryRepository.save(pantry);
        return PantryMapper.toEntity(updatedPantry);
    }

    //Deletar despensa
    public void deletePantry(String id){
        Pantry pantry = findPantryOrThrow(id);
        pantryRepository.delete(pantry);
    }

    //Buscar despensa ou lançar erro 404 caso não exista
    private Pantry findPantryOrThrow(String id){
        return pantryRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Despensa não encontrada com id: " + id));
    }
}
