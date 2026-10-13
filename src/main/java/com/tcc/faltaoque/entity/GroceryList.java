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
@Table(name = "grocery_lists")
public class GroceryList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date")
    private LocalDate date;

    @Column(length = 8)
    private String location;

    @Column(nullable = false)
    private Byte suggestion;

    @Column(name = "estimated_price", precision = 10, scale = 2)
    private BigDecimal estimatedPrice;

    @ManyToOne
    @JoinColumn(name = "pantry_id", nullable = false)
    private Pantry pantry;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

}
