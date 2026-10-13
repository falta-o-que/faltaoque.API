package com.tcc.faltaoque.service;

import com.tcc.faltaoque.dto.request.PantryRequest;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.entity.Color;
import com.tcc.faltaoque.entity.PantryInvite;
import com.tcc.faltaoque.mapper.PantryMapper;
import com.tcc.faltaoque.repository.PantryRepository;
import com.tcc.faltaoque.repository.ColorRepository;
import com.tcc.faltaoque.repository.PantryInviteRepository;
import com.tcc.faltaoque.exception.ColorNotFoundException;
import com.tcc.faltaoque.exception.PantryInviteNotFoundException;
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
    private final ColorRepository colorRepository;
    private final PantryInviteRepository pantryInviteRepository;

    //Criar despensa
    public PantryResponse createPantry(PantryRequest pantryRequest){
        Color color = colorRepository.findById(pantryRequest.color())
            .orElseThrow(() -> new ColorNotFoundException(pantryRequest.color()));
        PantryInvite pantryInvite = pantryInviteRepository.findById(pantryRequest.shareInvite())
            .orElseThrow(() -> new PantryInviteNotFoundException(pantryRequest.shareInvite()));

        Pantry pantry = PantryMapper.toEntity(pantryRequest, color, pantryInvite);
        Pantry savedPantry = pantryRepository.save(pantry);

        return PantryMapper.toResponse(
            savedPantry, savedPantry.getColor(), savedPantry.getShareInvite());
    }

    //Listar todas as despensas
    public List<PantryResponse> listAllPantries(){
        return pantryRepository.findAll()
            .stream()
            .map(pantry -> PantryMapper.toResponse(
                pantry, pantry.getColor(), pantry.getShareInvite()))
            .collect(Collectors.toList());
    }

    //Buscar por id
    public PantryResponse findPantryById(String id){
        Pantry pantry = findPantryOrThrow(id);
        return PantryMapper.toResponse(pantry, pantry.getColor(), pantry.getShareInvite());
    }

    //Editar despensa
    public PantryResponse updatePantry(String id, PantryRequest pantryRequest){
        Pantry pantry = findPantryOrThrow(id);
        Color color = colorRepository.findById(pantryRequest.color())
            .orElseThrow(() -> new ColorNotFoundException(pantryRequest.color()));

        pantry.setTitle(pantryRequest.title());
        pantry.setLocation(pantryRequest.location());
        pantry.setColor(color);

        Pantry updatedPantry = pantryRepository.save(pantry);
        return PantryMapper.toResponse(
            updatedPantry, updatedPantry.getColor(), updatedPantry.getShareInvite());
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
