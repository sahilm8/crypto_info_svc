package com.sahil.crypto.info.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CryptoDto {
    /*
    API Response:
    {
        "bitcoin": {
            "usd": 97330.07874144994,
            "usd_market_cap": 1927011265296.741,
            "usd_24h_vol": 101401045720.07246,
            "usd_24h_change": 0.16383923709845424,
            "last_updated_at": 1734752328
        }
    }
    */

    private Map<String, CryptoData> cryptoData = new HashMap<>();

    @JsonAnySetter
    public void setCryptoData(String name, CryptoData cryptoData) {
        this.cryptoData.put(name, cryptoData);
    }

    @Data
    public static class CryptoData {
        @JsonProperty("usd")
        private String usd;
        
        @JsonProperty("usd_market_cap")
        private String usdMarketCap;
        
        @JsonProperty("usd_24h_vol")
        private String usd24hVol;
        
        @JsonProperty("usd_24h_change")
        private String usd24hChange;
        
        @JsonProperty("last_updated_at")
        private String lastUpdatedAt;
    }
}
