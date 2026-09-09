package com.tcc.faltaoque.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "grocery_lists")
public class GroceryList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Byte suggestion;

    @ManyToMany
    @JoinTable(
            name = "grocery_lists_pantry_products",
            joinColumns = @JoinColumn(name = "grocery_list_id"),
            inverseJoinColumns = @JoinColumn(name = "pantry_product_id")
    )
    private List<PantryProduct> pantryProducts = new ArrayList<>();
}
