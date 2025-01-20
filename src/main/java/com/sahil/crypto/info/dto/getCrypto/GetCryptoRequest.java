package com.sahil.crypto.info.dto.getCrypto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCryptoRequest {
    @NotBlank(message = "name is required")
    private String name;
}
