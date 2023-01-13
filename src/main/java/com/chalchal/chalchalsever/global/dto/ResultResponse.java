package com.chalchal.chalchalsever.global.dto;

import com.chalchal.chalchalsever.global.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
public class ResultResponse<T> {

    @JsonProperty
    private final LocalDateTime timestamp = LocalDateTime.now();

    @Builder.Default
    @JsonProperty
    private int status = HttpStatus.OK.value();

    @JsonProperty
    private String message;

    @JsonProperty
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public static ResponseEntity<ResultResponse> toResponse() {
        return toResponse(ResultResponse.builder().build());
    }

    public static ResponseEntity<ResultResponse> toResponse(ResultResponse resultResponse) {
        return ResponseEntity
                .ok()
                .body(ResultResponse.builder()
                        .status(resultResponse.status)
                        .data(resultResponse.data)
                        .message(resultResponse.message)
                        .build()
                );
    }
}
