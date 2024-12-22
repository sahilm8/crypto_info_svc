package com.sahil.crypto.info.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class CryptoTs {
    private String name;
    private List<BigDecimal[]> data; // ohlc
}
