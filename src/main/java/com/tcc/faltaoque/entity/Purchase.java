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
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 8)
    private String location;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "total_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "total_products", nullable = false)
    private int totalProducts;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "missing_products", nullable = false)
    private LocalDate missingProducts;

    @ManyToOne
    @JoinColumn(name = "pantry_id", nullable = false)
    private Pantry pantry;
}
