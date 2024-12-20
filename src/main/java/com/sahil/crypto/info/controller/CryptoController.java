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
                        "- GET /get-crypto%n");
    }

    @GetMapping(value = "/get-crypto", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Crypto>> getCrypto(@RequestParam String symbol) {
        log.info("Received request to GET /get-global-quote with argument: " + symbol.trim());
        return cryptoService.getCrypto(symbol.trim()).map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
