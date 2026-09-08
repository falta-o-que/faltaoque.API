package com.tcc.faltaoque.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private Byte quantity;

    @Column(name = "is_in_pantry", nullable = false)
    private Boolean isInPantry;

    private Double weight;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length = 100)
    private String brand;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "missing_date")
    private LocalDate missingDate;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonIgnore
    @ManyToMany(mappedBy = "pantryProducts")
    private List<GroceryList> groceryLists = new ArrayList<>();
}
