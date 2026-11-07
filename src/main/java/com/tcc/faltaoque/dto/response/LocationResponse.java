package com.tcc.faltaoque.dto.response;

import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record LocationResponse(
        String id,
        String localName,
        String customName,
        String cep,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
