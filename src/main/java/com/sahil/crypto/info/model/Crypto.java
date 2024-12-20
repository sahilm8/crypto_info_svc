package com.sahil.crypto.info.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Crypto {
    private String symbol;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal price;
    private BigDecimal volume;
    private String latestTradingDay;
    private BigDecimal prevClose;
    private BigDecimal change;
    private BigDecimal changePercent;
}
