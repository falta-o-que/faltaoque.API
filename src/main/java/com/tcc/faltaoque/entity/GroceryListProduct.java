package com.tcc.faltaoque.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "grocery_list_products")
public class GroceryListProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    private int quantity;

    @Column(name = "content_value")
    private Double contentValue;

    @Column(name = "unit_of_measure")
    private Byte unitOfMeasure;

    @Column(name = "is_taken", nullable = false)
    private boolean isTaken;

    @ManyToOne
    @JoinColumn(name = "grocery_list_id", nullable = false)
    private GroceryList groceryList;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;



}
