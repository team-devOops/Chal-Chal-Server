package com.chalchal.chalchalsever.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ResultResponse {
    @JsonProperty
    private int status;

    @JsonProperty
    private String message;
}
