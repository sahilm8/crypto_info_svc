package com.sahil.crypto.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.crypto.info.dto.getCrypto.GetCryptoRequest;
import com.sahil.crypto.info.dto.getCrypto.GetCryptoResponse;
import com.sahil.crypto.info.dto.getCryptoTs.GetCryptoTsRequest;
import com.sahil.crypto.info.dto.getCryptoTs.GetCryptoTsResponse;
import com.sahil.crypto.info.model.Crypto;
import com.sahil.crypto.info.model.CryptoTs;
import com.sahil.crypto.info.service.CryptoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/crypto")
@RequiredArgsConstructor
@Validated
public class CryptoController {
    private final CryptoService cryptoService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        return String.format(
                "Crypto Info API%n%n" +
                        "Welcome to the /crypto endpoint, you can make the following requests:%n" +
                        "- GET /get-crypto%n" +
                        "- GET /get-crypto-ts%n");
    }

    @GetMapping(value = "/get-crypto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<GetCryptoResponse>> getCrypto(@Valid @RequestBody GetCryptoRequest getCryptoRequest) {
        return ResponseEntity.ok(cryptoService.getCrypto(getCryptoRequest));
    }

    @GetMapping(value = "/get-crypto-ts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<GetCryptoTsResponse>> getIntradayTs(
            @Valid @RequestBody GetCryptoTsRequest getCryptoTsRequest) {
        return ResponseEntity.ok(cryptoService.getCryptoTs(getCryptoTsRequest));
    }

    @GetMapping(value = "/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> invalid() {
        return ResponseEntity
                .ok("Invalid request, please refer to the README at https://github.com/sahilm8/crypto_info_svc");
    }
}
