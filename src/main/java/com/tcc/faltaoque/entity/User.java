package com.tcc.faltaoque.entity;

import com.tcc.faltaoque.enums.Avatar;
import com.tcc.faltaoque.enums.Role;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 254, nullable = false, unique = true)
    private String email;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(nullable = false)
    private Avatar avatar;

    @Column(nullable = false)
    @Builder.Default
    private Role role = Role.ROLE_USER;

    @ManyToMany
    @JoinTable(
            name = "users_pantries",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "pantry_id")
    )
    private List<Pantry> pantries = new ArrayList<>();
}
