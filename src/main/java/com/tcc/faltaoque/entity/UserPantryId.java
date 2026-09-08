package com.tcc.faltaoque.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserPantryId implements Serializable {

    @Column(name = "user_id", length = 36)
    private String userId;

    @Column(name = "pantry_id", length = 36)
    private String pantryId;
}