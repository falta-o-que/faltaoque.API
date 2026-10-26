package com.tcc.faltaoque.dto.request;

import java.math.BigDecimal;

public record LocationRequest (
        String localName,
        String customName,
        String cep,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
