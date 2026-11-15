package com.tcc.faltaoque.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PantryInviteRequest(

        @NotBlank(message = "Informe a despensa do convite")
        String pantryId) {

}
