package com.tcc.faltaoque.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pantries")
public class Pantry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(length = 8)
    private String location;

    @Column(nullable = false)
    private Byte color; //atualizar para classe depois que for feita a entidade Color

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "share_invite_id")
    private PantryInvite shareInvite;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "pantries")
    @Builder.Default
    private List<User> users = new ArrayList<>();


    //Ao excluir a despensa, as compras e as listas de compras são excluídas junto
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "pantry", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<UserPantry> userPantries = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "pantry", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Purchase> purchases = new ArrayList<>();
}
