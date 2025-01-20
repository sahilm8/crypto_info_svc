package com.sahil.crypto.info.dto.getCrypto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCryptoResponse {
    private BigDecimal price;
    private BigDecimal marketCap;
    private BigDecimal volume;
    private BigDecimal change;
    private Long lastUpdatedAt;
}
