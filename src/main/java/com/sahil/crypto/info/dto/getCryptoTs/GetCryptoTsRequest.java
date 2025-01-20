package com.sahil.crypto.info.dto.getCryptoTs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCryptoTsRequest {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "days is required")
    private String days;
}
