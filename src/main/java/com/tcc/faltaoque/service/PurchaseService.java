package com.tcc.faltaoque.service;

import com.tcc.faltaoque.dto.request.PurchaseRequest;
import com.tcc.faltaoque.dto.response.PurchaseResponse;
import com.tcc.faltaoque.entity.Location;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.entity.PantryProduct;
import com.tcc.faltaoque.entity.Purchase;
import com.tcc.faltaoque.exception.*;
import com.tcc.faltaoque.mapper.PurchaseMapper;
import com.tcc.faltaoque.repository.LocationRepository;
import com.tcc.faltaoque.repository.PantryProductRepository;
import com.tcc.faltaoque.repository.PantryRepository;
import com.tcc.faltaoque.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final LocationRepository locationRepository;
    private final PantryRepository pantryRepository;
    private final PantryProductRepository pantryProductRepository;


    //Função para atualizar totalPrice e totalProducts
    public void updatePurchaseTotals(String purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        List<PantryProduct> products = pantryProductRepository.findByPurchaseId(purchaseId);

        BigDecimal totalPrice = products.stream()
                .map(product -> product.getPrice().multiply(
                        BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int totalProducts = products.stream()
                .mapToInt(PantryProduct::getQuantity)
                .sum();

        purchase.setTotalPrice(totalPrice);
        purchase.setTotalProducts(totalProducts);

        purchaseRepository.save(purchase);
    }

    //Criar nova compra
    public PurchaseResponse createPurchase(PurchaseRequest purchaseRequest) {
        Location location = locationRepository.findById(purchaseRequest.location())
                .orElseThrow(() -> new LocationNotFoundException(purchaseRequest.location()));

        Pantry pantry = pantryRepository.findById(purchaseRequest.pantryId())
                .orElseThrow(() -> new PantryNotFoundException(purchaseRequest.pantryId()));

        Purchase purchase = PurchaseMapper.toEntity(purchaseRequest, location, pantry);
        Purchase savedPurchase = purchaseRepository.save(purchase);

        return PurchaseMapper.toResponse(
                savedPurchase, savedPurchase.getLocation(), savedPurchase.getPantry());
    }

    //Editar compra
    public PurchaseResponse purchaseUpdate(String id, PurchaseRequest purchaseRequest) {
        return purchaseRepository.findById(id)
                .map(existing -> locationRepository.findById(purchaseRequest.location())
                        .map(location -> pantryRepository.findById(purchaseRequest.pantryId())
                                .map(pantry -> {
                                    Purchase purchaseToUpdate = PurchaseMapper.toEntity(
                                            purchaseRequest, location, pantry
                                    );

                                    purchaseToUpdate.setId(existing.getId());

                                    Purchase updatedPurchase =
                                            purchaseRepository.save(purchaseToUpdate);

                                    return PurchaseMapper.toResponse(
                                            updatedPurchase,
                                            updatedPurchase.getLocation(),
                                            updatedPurchase.getPantry()
                                    );
                                })
                                .orElseThrow(() -> new PantryProductNotFoundException(purchaseRequest.pantryId())))
                        .orElseThrow(() -> new LocationNotFoundException(purchaseRequest.location())))
                .orElseThrow(() -> new PurchaseNotFoundException(id));
    }

    //Deletar compra
    public Purchase deleteById(String id) {
        purchaseRepository.findById(id)
                .orElseThrow(() -> new PurchaseNotFoundException(id));

        purchaseRepository.deleteById(id);
        return null;
    }

    //Listar compra por id
    public PurchaseResponse findById(String id) {
        return purchaseRepository.findById(id).map(purchase -> PurchaseMapper.toResponse(
                        purchase,
                        purchase.getLocation(),
                        purchase.getPantry()
                ))
                .orElseThrow(() -> new PurchaseNotFoundException(id));
    }

    //Listar todas as compras
    public List<PurchaseResponse> listAllPurchases() {
        return purchaseRepository.findAll()
                .stream()
                .map(purchase -> PurchaseMapper.toResponse(
                        purchase,
                        purchase.getLocation(),
                        purchase.getPantry()
                ))
                .toList();
    }

    //Listar compras de uma despensa com seus produtos
    public List<PurchaseResponse> listProductsByPurchase(String pantryId) {
        pantryRepository.findById(pantryId)
                .orElseThrow(() -> new PantryNotFoundException(pantryId));

        return purchaseRepository.findByPantryId(pantryId)
                .stream()
                .map(purchase -> PurchaseMapper.toResponse(
                        purchase,
                        purchase.getLocation(),
                        purchase.getPantry()
                ))
                .toList();
    }

}
