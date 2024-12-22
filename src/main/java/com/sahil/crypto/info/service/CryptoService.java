package com.sahil.crypto.info.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.crypto.info.dto.CryptoDto;
import com.sahil.crypto.info.dto.CryptoTsDto;
import com.sahil.crypto.info.model.Crypto;
import com.sahil.crypto.info.model.CryptoTs;

import jakarta.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CryptoService {
    @Value("${crypto.api_url}")
    private String apiUrl;

    @Value("${crypto.api_key}")
    private String apiKey;
    
    private WebClient webClient;

    @PostConstruct
    public void init() {
        ExchangeStrategies strategies = ExchangeStrategies
        .builder()
        .codecs(configurer -> 
            configurer
            .defaultCodecs()
            .maxInMemorySize(1024 * 1024 * 10) // 10MB
        )
        .build();

        webClient = WebClient
        .builder()
        .baseUrl(apiUrl)
        .defaultHeader("x_cg_demo_api_key", apiKey)
        .exchangeStrategies(strategies)
        .build();
    }

    public Mono<Crypto> getPrice(String name) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/v3/simple/price")
                .queryParam("ids", name)
                .queryParam("vs_currencies", "usd")
                .queryParam("include_market_cap", true)
                .queryParam("include_24hr_vol", true)
                .queryParam("include_24hr_change", true)
                .queryParam("include_last_updated_at", true)
                .queryParam("precision", "full")
                .build())
            .retrieve()
            .bodyToMono(CryptoDto.class)
            .map(dto -> {
                log.info("dto: " + dto);
                CryptoDto.CryptoData data = dto.getCryptoData().get(name);
                Crypto crypto = new Crypto();
                crypto.setName(name);
                crypto.setUsdPrice(data.getUsd() != null ? new BigDecimal(data.getUsd()) : null);
                crypto.setUsdMarketCap(data.getUsdMarketCap() != null ? new BigDecimal(data.getUsdMarketCap()) : null);
                crypto.setUsd24hVolume(data.getUsd24hVol() != null ? new BigDecimal(data.getUsd24hVol()) : null);
                crypto.setUsd24hChange(data.getUsd24hChange() != null ? new BigDecimal(data.getUsd24hChange()) : null);
                crypto.setLastUpdatedAt(data.getLastUpdatedAt() != null ? Long.parseLong(data.getLastUpdatedAt()) : null);
                return crypto;
            });
    }

    public Mono<CryptoTs> getOhlc(String name, String days) {
        return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/api/v3/coins/" + name + "/ohlc")
            .queryParam("vs_currency", "usd")
            .queryParam("days", days)
            .queryParam("precision", "full")
            .build())
        .retrieve()
        .bodyToMono(CryptoTsDto.class)
        .map(dto -> {
            log.info("dto: " + dto);
            CryptoTs cryptoTs = new CryptoTs();
            List<BigDecimal[]> data = dto.stream()
            .map(entry -> {
                BigDecimal[] ohlc = new BigDecimal[4];
                ohlc[0] = new BigDecimal(entry[1].toString());
                ohlc[1] = new BigDecimal(entry[2].toString());
                ohlc[2] = new BigDecimal(entry[3].toString());
                ohlc[3] = new BigDecimal(entry[4].toString());
                return ohlc;
                })
            .collect(Collectors.toList());
            cryptoTs.setName(name);
            cryptoTs.setData(data);
            return cryptoTs;
        });
    }
}
