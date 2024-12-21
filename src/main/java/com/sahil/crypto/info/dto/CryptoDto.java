package com.sahil.crypto.info.dto;

import java.util.Map;

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

    private Map<String, Map<String, String>> cryptoData;
    
    public String getUsdPrice(String name) {
        return cryptoData.get(name).get("usd");
    }

    public String getUsdMarketCap(String name) {
        return cryptoData.get(name).get("usd_market_cap");
    }

    public String getUsd24hVol(String name) {
        return cryptoData.get(name).get("usd_24h_vol");
    }

    public String getUsd24hChange(String name) {
        return cryptoData.get(name).get("usd_24h_change");
    }

    public String getLastUpdatedAt(String name) {
        return cryptoData.get(name).get("last_updated_at");
    }
}
