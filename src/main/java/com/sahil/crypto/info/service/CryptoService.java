package com.sahil.crypto.info.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.crypto.info.dto.getCrypto.GetCryptoApiResponse;
import com.sahil.crypto.info.dto.getCrypto.GetCryptoApiResponse.Crypto;
import com.sahil.crypto.info.dto.getCrypto.GetCryptoRequest;
import com.sahil.crypto.info.dto.getCrypto.GetCryptoResponse;
import com.sahil.crypto.info.dto.getCryptoTs.GetCryptoTsApiResponse;
import com.sahil.crypto.info.dto.getCryptoTs.GetCryptoTsRequest;
import com.sahil.crypto.info.dto.getCryptoTs.GetCryptoTsResponse;

import jakarta.annotation.PostConstruct;

import reactor.core.publisher.Mono;

@Service
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
                .codecs(configurer -> configurer
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

    public Mono<GetCryptoResponse> getCrypto(GetCryptoRequest getCryptoRequest) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v3/simple/price")
                        .queryParam("ids", getCryptoRequest.getName())
                        .queryParam("vs_currencies", "usd")
                        .queryParam("include_market_cap", true)
                        .queryParam("include_24hr_vol", true)
                        .queryParam("include_24hr_change", true)
                        .queryParam("include_last_updated_at", true)
                        .queryParam("precision", "full")
                        .build())
                .retrieve()
                .bodyToMono(GetCryptoApiResponse.class)
                .map((getCryptoApiResponse) -> {
                    Crypto crypto = getCryptoApiResponse.getCrypto().get(getCryptoRequest.getName());
                    return GetCryptoResponse.builder()
                            .price(crypto.getPrice())
                            .marketCap(crypto.getMarketCap())
                            .volume(crypto.getVolume())
                            .change(crypto.getChange())
                            .lastUpdatedAt(crypto.getLastUpdatedAt())
                            .build();
                });
    }

    public Mono<GetCryptoTsResponse> getCryptoTs(GetCryptoTsRequest getCryptoTsRequest) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v3/coins/" + getCryptoTsRequest.getName() + "/ohlc")
                        .queryParam("vs_currency", "usd")
                        .queryParam("days", getCryptoTsRequest.getDays())
                        .queryParam("precision", "full")
                        .build())
                .retrieve()
                .bodyToMono(GetCryptoTsApiResponse.class)
                .map((getCryptoTsApiResponse) -> {
                    GetCryptoTsResponse getCryptoTsResponse = GetCryptoTsResponse.builder().build();
                    getCryptoTsResponse.addAll(getCryptoTsApiResponse);
                    return getCryptoTsResponse;
                });
    }
}
