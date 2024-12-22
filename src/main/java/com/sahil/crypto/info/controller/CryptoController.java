package com.sahil.crypto.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.crypto.info.model.Crypto;
import com.sahil.crypto.info.model.CryptoTs;
import com.sahil.crypto.info.service.CryptoService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/crypto")
@Slf4j
public class CryptoController {
    @Autowired
    private CryptoService cryptoService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        log.info("Received request to GET /.");
        return String.format(
                "Crypto Info API%n%n" +
                        "Welcome to the crypto endpoint, you can make the following requests:%n" +
                        "- GET /get-price%n" +
                        "- GET /get-ohlc%n");
    }

    @GetMapping(value = "/get-price", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Crypto>> getPrice(@RequestParam String name) {
        log.info("Received request to GET /get-price with argument: " + name.trim());
        return cryptoService.getPrice(name.trim()).map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(value = "/get-ohlc", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CryptoTs>> getOhlc(@RequestParam String name, @RequestParam String days) {
        log.info("Received request to GET /get-ohlc with arguments: " + name.trim() + ", " + days.trim());
        return cryptoService.getOhlc(name.trim(), days.trim()).map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
