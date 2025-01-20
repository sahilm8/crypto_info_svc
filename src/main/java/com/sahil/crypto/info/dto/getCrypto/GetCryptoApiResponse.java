package com.sahil.crypto.info.dto.getCrypto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCryptoApiResponse {
    /*
     * API Response:
     * {
     * "bitcoin": {
     * "usd": 97330.07874144994,
     * "usd_market_cap": 1927011265296.741,
     * "usd_24h_vol": 101401045720.07246,
     * "usd_24h_change": 0.16383923709845424,
     * "last_updated_at": 1734752328
     * }
     * }
     */

    private Map<String, Crypto> crypto;

    @JsonAnySetter
    public void setCrypto(String name, Crypto crypto) {
        this.crypto = new HashMap<>();
        this.crypto.put(name, crypto);
    }

    @Data
    public static class Crypto {
        @JsonProperty("usd")
        private BigDecimal price;

        @JsonProperty("usd_market_cap")
        private BigDecimal marketCap;

        @JsonProperty("usd_24h_vol")
        private BigDecimal volume;

        @JsonProperty("usd_24h_change")
        private BigDecimal change;

        @JsonProperty("last_updated_at")
        private String lastUpdatedAt;
    }
}
