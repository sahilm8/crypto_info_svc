package com.sahil.crypto.info.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CryptoDto {
    /*
    API Response:
    {
        "Global Quote": {
        "01. symbol": "IBM",
        "02. open": "234.4300",
        "03. high": "238.3800",
        "04. low": "234.2200",
        "05. price": "238.0400",
        "06. volume": "4028430",
        "07. latest trading day": "2024-12-06",
        "08. previous close": "234.7500",
        "09. change": "3.2900",
        "10. change percent": "1.4015%"
        }
    }
    */

    @JsonProperty("Global Quote")
    private Map<String, String> globalQuote = new HashMap<>();

    public String getSymbol() {
        return globalQuote.get("01. symbol");
    }

    public BigDecimal getOpen() {
        String value = globalQuote.get("02. open");
        return value != null ? new BigDecimal(value) : BigDecimal.ZERO;
    }

    public BigDecimal getHigh() {
        String value = globalQuote.get("03. high");
        return value != null ? new BigDecimal(value) : BigDecimal.ZERO;
    }

    public BigDecimal getLow() {
        String value = globalQuote.get("04. low");
        return value != null ? new BigDecimal(value) : BigDecimal.ZERO;
    }

    public BigDecimal getPrice() {
        String value = globalQuote.get("05. price");
        return value != null ? new BigDecimal(value) : BigDecimal.ZERO;
    }

    public BigDecimal getVolume() {
        String value = globalQuote.get("06. volume");
        return value != null ? new BigDecimal(value) : BigDecimal.ZERO;
    }

    public String getLatestTradingDay() {
        return globalQuote.get("07. latest trading day");
    }

    public BigDecimal getPrevClose() {
        String value = globalQuote.get("08. previous close");
        return value != null ? new BigDecimal(value) : BigDecimal.ZERO;
    }

    public BigDecimal getChange() {
        String value = globalQuote.get("09. change");
        return value != null ? new BigDecimal(value) : BigDecimal.ZERO;
    }

    public BigDecimal getChangePercent() {
        String value = globalQuote.get("10. change percent");
        return value != null ? new BigDecimal(value.replace("%", "")) : BigDecimal.ZERO;
    }
}
