package com.tcc.faltaoque.service;

import com.tcc.faltaoque.dto.request.PantryProductRequest;
import com.tcc.faltaoque.dto.response.PantryProductResponse;
import com.tcc.faltaoque.entity.Category;
import com.tcc.faltaoque.entity.PantryProduct;
import com.tcc.faltaoque.entity.Purchase;
import com.tcc.faltaoque.exception.CategoryNotFoundException;
import com.tcc.faltaoque.exception.PantryInviteNotFoundException;
import com.tcc.faltaoque.exception.PantryProductNotFoundException;
import com.tcc.faltaoque.exception.PurchaseNotFoundException;
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
        return pantryProductRepository.findById(id)
                .map(existing -> purchaseRepository.findById(pantryProductRequest.purchase())
                        .map(purchase -> categoryRepository.findById(pantryProductRequest.category())
                                .map(category -> {
                                    PantryProduct pantryProductToUpdate = PantryProductMapper.toEntity(
                                            pantryProductRequest, purchase, category
                                    );

                                    pantryProductToUpdate.setId(existing.getId());

                                    PantryProduct updatedPantryProduct =
                                            pantryProductRepository.save(pantryProductToUpdate);

                                    purchaseService.updatePurchaseTotals(updatedPantryProduct.getPurchase().getId());

                                    return PantryProductMapper.toResponse(
                                            updatedPantryProduct,
                                            updatedPantryProduct.getPurchase(),
                                            updatedPantryProduct.getCategory()
                                    );

                                }).orElseThrow(() -> new CategoryNotFoundException(pantryProductRequest.category())))
                        .orElseThrow(() -> new PurchaseNotFoundException(pantryProductRequest.purchase())))
                .orElseThrow(() -> new PantryInviteNotFoundException(id));
    }

    //Deletar produto de uma compra
    public void deleteProductByPurchase(String purchaseId, String productId) {
        PantryProduct product = pantryProductRepository
                .findByIdAndPurchaseId(productId, purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        pantryProductRepository.delete(product);

        purchaseService.updatePurchaseTotals(product.getPurchase().getId());
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

    //Listar produtos de uma compra
    public List<PantryProductResponse> listProductsByPurchase(String purchaseId) {
        purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        return pantryProductRepository.findByPurchaseId(purchaseId)
                .stream()
                .map(purchaseProduct -> PantryProductMapper.toResponse(
                        purchaseProduct,
                        purchaseProduct.getPurchase(),
                        purchaseProduct.getCategory()
                ))
                .toList();
    }

    //Listar produtos por categoria
    public List<PantryProductResponse> listProductsByPurchaseAndCategory(String purchaseId, int categoryId) {
        purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

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
