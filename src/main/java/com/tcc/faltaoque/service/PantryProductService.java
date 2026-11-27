package com.tcc.faltaoque.service;

import com.tcc.faltaoque.dto.request.PantryProductRequest;
import com.tcc.faltaoque.dto.response.PantryProductDeleteResponse;
import com.tcc.faltaoque.dto.response.PantryProductResponse;
import com.tcc.faltaoque.entity.Category;
import com.tcc.faltaoque.entity.PantryProduct;
import com.tcc.faltaoque.entity.Purchase;
import com.tcc.faltaoque.exception.*;
import com.tcc.faltaoque.mapper.PantryProductMapper;
import com.tcc.faltaoque.repository.CategoryRepository;
import com.tcc.faltaoque.repository.PantryProductRepository;
import com.tcc.faltaoque.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PantryProductService {

    private final PantryProductRepository pantryProductRepository;
    private final PurchaseRepository purchaseRepository;
    private final CategoryRepository categoryRepository;
    private final PurchaseService purchaseService;

    //Adicionar produtos
    public PantryProductResponse addProduct(PantryProductRequest pantryProductRequest) {
        Purchase purchase = purchaseRepository.findById(pantryProductRequest.purchase())
                .orElseThrow(() -> new PurchaseNotFoundException(pantryProductRequest.purchase()));

        Category category = categoryRepository.findById(pantryProductRequest.category())
                .orElseThrow(() -> new CategoryNotFoundException(pantryProductRequest.category()));

        PantryProduct pantryProduct = PantryProductMapper.toEntity(
                pantryProductRequest, purchase, category);

        PantryProduct savedProduct = pantryProductRepository.save(pantryProduct);

        purchaseService.updatePurchaseTotals(savedProduct.getPurchase().getId());

        return PantryProductMapper.toResponse(
                savedProduct,
                savedProduct.getPurchase(),
                savedProduct.getCategory()
        );
    }

    //Editar produto
    public PantryProductResponse productUpdate(String id, PantryProductRequest pantryProductRequest) {
        PantryProduct product = pantryProductRepository.findById(id)
                .orElseThrow(() -> new PantryProductNotFoundException(id));

        Purchase purchase = purchaseRepository.findById(pantryProductRequest.purchase())
                .orElseThrow(() -> new PurchaseNotFoundException(pantryProductRequest.purchase()));

        Category category = categoryRepository.findById(pantryProductRequest.category())
                .orElseThrow(() -> new CategoryNotFoundException(pantryProductRequest.category()));

        PantryProductMapper.updateEntity(product, pantryProductRequest);

        product.setPurchase(purchase);
        product.setCategory(category);

        pantryProductRepository.save(product);

        purchaseService.updatePurchaseTotals(purchase.getId());

        return PantryProductMapper.toResponse(product, purchase, category);
    }

    //Deletar produto
    public PantryProductDeleteResponse deletePurchaseProduct(String productId) {
        PantryProduct product = pantryProductRepository
                .findById(productId)
                        .orElseThrow(() -> new PantryProductNotFoundException(productId));

        product.setDeleted(true);

        pantryProductRepository.save(product);

        purchaseService.updatePurchaseTotals(product.getPurchase().getId());

        return PantryProductMapper.pantryProductDeleteResponse(product);
    }

    //Listar produto por id
    public PantryProductResponse findById(String id) {
        return pantryProductRepository.findById(id).map(pantryProduct -> PantryProductMapper.toResponse(
                        pantryProduct,
                        pantryProduct.getPurchase(),
                        pantryProduct.getCategory()
                ))
                .orElseThrow(() -> new PantryProductNotFoundException(id));

    }

    //Listar todos os produtos
    public List<PantryProductResponse> listAllProducts() {
        return pantryProductRepository.findAll()
                .stream()
                .map(pantryProduct -> PantryProductMapper.toResponse(
                        pantryProduct,
                        pantryProduct.getPurchase(),
                        pantryProduct.getCategory()
                ))
                .toList();
    }

    //Listar produtos por categoria
    public List<PantryProductResponse> listProductsByPurchaseAndCategory(String purchaseId, int categoryId) {
        purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        List<PantryProduct> products =
                pantryProductRepository.findByPurchaseIdAndCategoryId(purchaseId, categoryId);

        if (products.isEmpty()) {
            throw new ProductCategoryNotFoundException(categoryId);
        }

        return pantryProductRepository
                .findByPurchaseIdAndCategoryId(purchaseId, categoryId)
                .stream()
                .map(product -> PantryProductMapper.toResponse(
                        product,
                        product.getPurchase(),
                        product.getCategory()))
                .toList();
    }

    //Filtar produtos por nome
    public List<PantryProductResponse> filterByName(String purchaseId, String name) {
        purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        List<PantryProduct> products =
                pantryProductRepository.findByPurchaseIdAndName(purchaseId, name);

        if (products.isEmpty()) {
            throw new ProductNameNotFoundException(name);
        }

        return pantryProductRepository
                .findByPurchaseIdAndName(purchaseId, name)
                .stream()
                .map(product -> PantryProductMapper.toResponse(
                        product,
                        product.getPurchase(),
                        product.getCategory()))
                .toList();
    }

}
