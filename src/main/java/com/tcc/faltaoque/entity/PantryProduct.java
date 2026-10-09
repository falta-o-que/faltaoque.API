package com.tcc.faltaoque.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pantry_products")
public class PantryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    @Builder.Default
    private int quantity = 1;

    @Column(name = " current_quantity", nullable = false)
    @Builder.Default
    private int currentQuantity = 1;

    @Column(name = "is_in_pantry", nullable = false)
    @Builder.Default
    private Boolean isInPantry = true;

    @Column(name = "content_value")
    private Double contentValue;

    @Column(name = "unit_of_measure")
    private Byte unitOfMeasure;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length = 100)
    private String brand;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "finish_date")
    private LocalDate finishDate;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
