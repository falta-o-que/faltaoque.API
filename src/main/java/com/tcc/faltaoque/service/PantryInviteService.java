package com.tcc.faltaoque.service;

import com.tcc.faltaoque.dto.request.PantryInviteRequest;
import com.tcc.faltaoque.dto.response.PantryInviteResponse;
import com.tcc.faltaoque.dto.response.PantryResponse;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.entity.PantryInvite;
import com.tcc.faltaoque.entity.User;
import com.tcc.faltaoque.exception.PantryInviteNotFoundException;
import com.tcc.faltaoque.mapper.PantryInviteMapper;
import com.tcc.faltaoque.mapper.PantryMapper;
import com.tcc.faltaoque.repository.PantryInviteRepository;
import com.tcc.faltaoque.repository.PantryRepository;
import com.tcc.faltaoque.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PantryInviteService {

    private final PantryInviteRepository pantryInviteRepository;
    private final PantryRepository pantryRepository;
    private final UserRepository userRepository;
    private final PantryService pantryService;

    //Criar o convite (link) de uma despensa. Cada despensa tem apenas um convite
    @Transactional
    public PantryInviteResponse createInvite(PantryInviteRequest pantryInviteRequest, String userEmail){
        Pantry pantry = pantryService.findPantryForUserOrThrow(pantryInviteRequest.pantryId(), userEmail);

        PantryInvite invite = pantryInviteRepository.save(PantryInvite
                .builder()
                .shareInvite(UUID.randomUUID().toString())
                .build());

        pantry.setShareInvite(invite);
        pantryRepository.save(pantry);

        return PantryInviteMapper.toEntity(invite);
    }

    //Listar todos os convites
    public List<PantryInviteResponse> listAllInvites(){
        return pantryInviteRepository.findAll()
                .stream()
                .map(PantryInviteMapper::toEntity)
                .collect(Collectors.toList());
    }

    //Buscar por id
    public PantryInviteResponse findInviteById(String id, String userEmail){
        PantryInvite invite = findInviteOrThrow(id);
        findPantryOfInviteOrThrow(invite, userEmail);
        return PantryInviteMapper.toEntity(invite);
    }


    //Deletar convite
    @Transactional
    public void deleteInvite(String id, String userEmail){
        PantryInvite invite = findInviteOrThrow(id);
        Pantry pantry = findPantryOfInviteOrThrow(invite, userEmail);

        pantry.setShareInvite(null);
        pantryRepository.save(pantry);
        pantryInviteRepository.delete(invite);
    }

    //Entrar em uma despensa pelo link, desde que o convite esteja ativo e não tenha expirado
    public PantryResponse joinPantry(String shareInvite, String userEmail){
        User user = pantryService.findUserOrThrow(userEmail);

        PantryInvite invite = pantryInviteRepository.findByShareInvite(shareInvite).orElseThrow(()
                -> new PantryInviteNotFoundException("Convite não encontrado para o link informado."));

        if (invite.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.GONE, "O convite expirou.");
        }

        Pantry pantry = pantryRepository.findFirstByShareInvite_Id(invite.getId()).orElseThrow(()
                -> new PantryInviteNotFoundException("Convite não encontrado para o link informado."));

        if (pantryRepository.existsByIdAndUsers_Email(pantry.getId(), userEmail)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Você já participa desta despensa.");
        }

        user.getPantries().add(pantry);
        userRepository.save(user);

        return PantryMapper.toEntity(pantry);
    }

    //Remover, de hora em hora, os convites expirados
    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void deleteExpiredInvites(){
        LocalDateTime now = LocalDateTime.now();
        pantryRepository.detachExpiredInvites(now);
        pantryInviteRepository.deleteExpired(now);
    }

    //Buscar convite ou lançar erro 404 caso não exista
    private PantryInvite findInviteOrThrow(String id){
        return pantryInviteRepository.findById(id).orElseThrow(()
                -> new PantryInviteNotFoundException("Convite não encontrado com o id: " + id));
    }

    //Buscar a despensa do convite (404 sem despensa, 403 se não for convidado)
    private Pantry findPantryOfInviteOrThrow(PantryInvite invite, String userEmail){
        Pantry pantry = pantryRepository.findFirstByShareInvite_Id(invite.getId()).orElseThrow(()
                -> new PantryInviteNotFoundException("Convite não encontrado com o id: " + invite.getId()));
        return pantryService.findPantryForUserOrThrow(pantry.getId(), userEmail);
    }
}
