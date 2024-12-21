package com.sahil.crypto.info.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.crypto.info.dto.CryptoDto;
import com.sahil.crypto.info.model.Crypto;

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
            .maxInMemorySize(1024 * 1024 * 10)  // 10MB
        )
        .build();

        webClient = WebClient
        .builder()
        .baseUrl(apiUrl)
        .defaultHeader("x_cg_demo_api_key", apiKey)
        .exchangeStrategies(strategies)
        .build();
    }

    public Mono<Crypto> getPrice(String symbol) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
            .path("//api/v3/simple/price")
            .queryParam("ids", symbol)
            .queryParam("vs_currencies", "USD")
            .build())
            .retrieve()
            .bodyToMono(CryptoDto.class)
            .map(dto -> {
                log.info("dto: " + dto);
                return null;
            });
    }
}
