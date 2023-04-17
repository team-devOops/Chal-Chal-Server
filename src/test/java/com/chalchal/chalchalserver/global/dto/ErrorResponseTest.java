package com.chalchal.chalchalserver.global.dto;

import com.chalchal.chalchalserver.global.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

class ErrorResponseTest {
    @Test
    void toErrorResponse() {
        //given
        ErrorCode errorCode = ErrorCode.INVALID_REFRESH_TOKEN;

        //when
        ResponseEntity<ErrorResponse> responseEntity = ErrorResponse.toErrorResponse(errorCode);
        ErrorResponse response = responseEntity.getBody();

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getError()).isEqualTo(HttpStatus.BAD_REQUEST.name());
        assertThat(response.getCode()).isEqualTo(errorCode.name());
        assertThat(response.getMessage()).isEqualTo(errorCode.getDetail());
    }
}