package com.tcc.faltaoque.service;

import com.tcc.faltaoque.dto.request.PurchaseRequest;
import com.tcc.faltaoque.dto.response.PantryProductResponse;
import com.tcc.faltaoque.dto.response.PurchaseResponse;
import com.tcc.faltaoque.entity.Location;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.entity.PantryProduct;
import com.tcc.faltaoque.entity.Purchase;
import com.tcc.faltaoque.exception.*;
import com.tcc.faltaoque.mapper.PantryProductMapper;
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
        String qrCodeId = purchaseRequest.qrCodeId();

        if (qrCodeId != null && purchaseRepository.findByQrCodeId(qrCodeId).isPresent()) {
            throw new AlreadyExistingPurchaseException(qrCodeId);
        }

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
      Purchase purchase = purchaseRepository.findById(id)
              .orElseThrow(() -> new PurchaseNotFoundException(id));

      Location location = locationRepository.findById(purchaseRequest.location())
              .orElseThrow(() -> new LocationNotFoundException(purchaseRequest.location()));

      Pantry pantry = pantryRepository.findById(purchaseRequest.pantryId())
              .orElseThrow(() -> new PantryNotFoundException(purchaseRequest.pantryId()));

      PurchaseMapper.updateEntity(purchase, purchaseRequest);

      purchase.setLocation(location);
      purchase.setPantry(pantry);

      purchaseRepository.save(purchase);

      return PurchaseMapper.toResponse(purchase,location, pantry);


    }

    //Deletar compra
    public void deleteById(String id) {
        purchaseRepository.findById(id)
                .orElseThrow(() -> new PurchaseNotFoundException(id));

        purchaseRepository.deleteById(id);
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

    public List<PantryProductResponse> listProductsByPurchase(String purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        return pantryProductRepository.findByPurchaseId(purchase.getId())
                .stream()
                .map(product -> PantryProductMapper.toResponse(
                        product,
                        product.getPurchase(),
                        product.getCategory()
                ))
                .toList();
    }

}
