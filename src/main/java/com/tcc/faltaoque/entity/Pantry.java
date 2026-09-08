package com.tcc.faltaoque.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Table(name = "pantries")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pantry {

    @Id
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private String id; //VARCHAR sem autoincrement

    @NotBlank(message = "O título da despensa é obrigatório")
    @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Size(max = 8, message = "A localização deve ter no máximo 8 caracteres")
    @Column(name = "location", length = 8)
    private String location;

    @NotNull(message = "A cor da despensa é obrigatória")
    @Column(name = "color", nullable = false)
    private Integer color;

    @Size(max = 36)
    @Column(name = "share_invite_id", length = 36)
    private String shareInviteId;

    @PrePersist //gera o UUID do id antes de salvar, já que o banco não faz automaticamente
    private void ensureId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
