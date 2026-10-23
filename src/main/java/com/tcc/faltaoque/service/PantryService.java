package com.tcc.faltaoque.service;

import com.tcc.faltaoque.dto.request.PantryRequest;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.entity.User;
import com.tcc.faltaoque.mapper.PantryMapper;
import com.tcc.faltaoque.repository.PantryInviteRepository;
import com.tcc.faltaoque.repository.PantryRepository;
import com.tcc.faltaoque.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PantryService {

    private final PantryRepository pantryRepository;
    private final UserRepository userRepository;

    //Criar despensa associada ao usuario autenticado
    @Transactional
    public PantryResponse createPantry(PantryRequest pantryRequest, String userEmail){
        User user = findUserOrThrow(userEmail);

        Pantry pantry = pantryRepository.save(PantryMapper.toRequest(pantryRequest, user));
        user.getPantries().add(pantry);
        pantry.getUsers().add(user);
        userRepository.save(user);

        return PantryMapper.toEntity(pantry);

    }

    //Listar todas as despensas
    public List<PantryResponse> listAllPantries(){
        return pantryRepository.findAll()
                .stream()
                .map(PantryMapper::toEntity)
                .collect(Collectors.toList());

    }

    //Buscar por id
    public PantryResponse findPantryById(String id, String userEmail){
        Pantry pantry = findPantryForUserOrThrow(id, userEmail);
        return PantryMapper.toEntity(pantry);
    }

    //Editar despensa
    public PantryResponse updatePantry(String id, PantryRequest pantryRequest, String userEmail){
        Pantry pantry = findPantryForUserOrThrow(id, userEmail);

        pantry.setTitle(pantryRequest.title());
        pantry.setLocation(pantryRequest.location());
        pantry.setColor(pantryRequest.color());


        Pantry updatedPantry = pantryRepository.save(pantry);
        return PantryMapper.toEntity(updatedPantry);
    }

    //Deletar despensa
    public void deletePantry(String id, String userEmail) {
        Pantry pantry = findPantryForUserOrThrow(id, userEmail);
        pantryRepository.delete(pantry);
    }

    //Buscar despensa ou lançar erro 404 caso não exista
    private Pantry findPantryOrThrow(String id){
        return pantryRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Despensa não encontrada com id: " + id));
    }

    //Buscar despensa ou lançar erro 404 caso não exista e 403 caso o usuário não participe dela
    Pantry findPantryForUserOrThrow(String id, String userEmail){
        Pantry pantry = findPantryOrThrow(id);

        if (!pantryRepository.existsByIdAndUsers_Email(id, userEmail)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não participa desta despensa.");
        }
        return pantry;
    }

    //Buscar usuário pelo e-mail ou lançar erro 404 caso não exista
    User findUserOrThrow(String userEmail){
        return userRepository.findByEmail(userEmail).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
    }
}
