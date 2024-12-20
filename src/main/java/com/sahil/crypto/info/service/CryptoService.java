package com.sahil.crypto.info.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.crypto.info.dto.CryptoDto;
import com.sahil.crypto.info.model.Crypto;
import com.sahil.crypto.info.util.ApiFunctions;

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
        .exchangeStrategies(strategies)
        .build();
    }

    public Mono<Crypto> getCrypto(String symbol) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
            .queryParam("function", ApiFunctions.GLOBAL_QUOTE.getValue())
            .queryParam("symbol", symbol)
            // datatype: json or csv
            .queryParam("datatype", "json")
            .queryParam("apikey", apiKey)
            .build())
            .retrieve()
            .bodyToMono(CryptoDto.class)
            .map(dto -> {
                log.info("dto: " + dto);
                return null;
            });
    }
}
