package com.sahil.crypto.info.dto.getCryptoTs;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;

@Builder
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class GetCryptoTsApiResponse extends ArrayList<String[]> {
    /*
     * API Response:
     * [
     * [
     * 1703116800000,
     * 42276.37291548006,
     * 44200.804096889355,
     * 42245.60532013463,
     * 43634.124168496404
     * ],
     * [
     * 1703462400000,
     * 43659.922052764814,
     * 44243.310825946384,
     * 42895.12500012594,
     * 43034.971063091514
     * ],
     * [
     * 1703808000000,
     * 42999.490588797315,
     * 43788.62252133361,
     * 41820.13108225962,
     * 42600.65063315619
     * ],
     * ]
     */
}