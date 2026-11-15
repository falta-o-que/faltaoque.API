package com.tcc.faltaoque.service;

import com.tcc.faltaoque.dto.request.PantryRequest;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.entity.Color;
import com.tcc.faltaoque.entity.User;
import com.tcc.faltaoque.mapper.PantryMapper;
import com.tcc.faltaoque.repository.PantryRepository;
import com.tcc.faltaoque.repository.ColorRepository;
import com.tcc.faltaoque.exception.ColorNotFoundException;
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
    private final ColorRepository colorRepository;
    private final UserRepository userRepository;


    //Criar despensa
    @Transactional
    public PantryResponse createPantry(PantryRequest pantryRequest, String userEmail){
        User user = findUserOrThrow(userEmail);
        Color color = findColorOrThrow(pantryRequest.color());

        Pantry pantry = pantryRepository.save(PantryMapper.toEntity(pantryRequest, color));
        user.getPantries().add(pantry);
        pantry.getUsers().add(user);
        userRepository.save(user);

        return PantryMapper.toResponse(
            pantry, pantry.getColor(), pantry.getShareInvite());
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
    public PantryResponse findPantryById(String id, String userEmail){
        Pantry pantry = findPantryForUserOrThrow(id, userEmail);
        return PantryMapper.toResponse(pantry, pantry.getColor(), pantry.getShareInvite());
    }

    //Editar despensa
    public PantryResponse updatePantry(String id, PantryRequest pantryRequest, String userEmail){
        Pantry pantry = findPantryForUserOrThrow(id, userEmail);
        Color color = findColorOrThrow(pantryRequest.color());

        pantry.setTitle(pantryRequest.title());
        pantry.setCep(pantryRequest.cep());
        pantry.setColor(color);

        Pantry updatedPantry = pantryRepository.save(pantry);
        return PantryMapper.toResponse(
            updatedPantry, updatedPantry.getColor(), updatedPantry.getShareInvite());
    }

    //Deletar despensa
    @Transactional
    public void deletePantry(String id, String userEmail){
        Pantry pantry = findPantryForUserOrThrow(id, userEmail);

        //Remove o vínculo com os usuários (users_pantries) antes de excluir a despensa
        pantry.getUsers().forEach(user -> user.getPantries().removeIf(p -> p.getId().equals(id)));
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

    //Buscar cor pelo id ou lançar erro 404 caso não exista
    private Color findColorOrThrow(int id){
        return colorRepository.findById(id).orElseThrow(()
                -> new ColorNotFoundException(id));
    }
}
