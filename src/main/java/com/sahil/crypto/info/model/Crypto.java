package com.sahil.crypto.info.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Crypto {
    private String name;
    private BigDecimal usdPrice;
    private BigDecimal usdMarketCap;
    private BigDecimal usd24hVolume;
    private BigDecimal usd24hChange;
    private Long lastUpdatedAt; // UNIX timestamp
}
