package com.chalchal.chalchalsever.global.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
public class ResultResponse<T> {

    @JsonProperty
    private final LocalDateTime timestamp = LocalDateTime.now();

    @JsonProperty
    private int status;

    @JsonProperty
    private String message;

    @JsonProperty
    private T data;

    public void setData(T data) {
        this.data = data;
    }
}
