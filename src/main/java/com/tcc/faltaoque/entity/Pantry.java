package com.tcc.faltaoque.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "pantries")
public class Pantry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(length = 8)
    private String location;

    @ManyToOne
    @JoinColumn(name="color_id", nullable = false)
    private Color colorId;

    @OneToOne
    @JoinColumn(name = "share_invite_id")
    private PantryInvite shareInvite;

    @JsonIgnore
    @ManyToMany(mappedBy = "pantries")
    private List<User> users = new ArrayList<>();
}
