package com.chalchal.chalchalserver.global.dto;

import com.chalchal.chalchalserver.global.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    @JsonProperty
    private final LocalDateTime timestamp = LocalDateTime.now();

    @JsonProperty
    private final int status;

    @JsonProperty
    private final String error;

    @JsonProperty
    private final String code;

    @JsonProperty
    private final String message;

    public static ResponseEntity<ErrorResponse> toErrorResponse(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getDetail())
                        .build()
                );
    }
}
