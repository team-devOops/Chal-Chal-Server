package com.chalchal.chalchalserver.global.dto;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ResultResponseTest {

    @Test
    void okResponse() {
        ResponseEntity<ResultResponse<Void>> responseEntity = ResultResponse.ok();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ResultResponse<Void> resultResponse = responseEntity.getBody();

        assertSoftly(softAssertions -> {
            assertThat(resultResponse).isNotNull();
            assertThat(resultResponse.getTimestamp().getYear()).isEqualTo(LocalDateTime.now().getYear());
            assertThat(resultResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
            assertThat(resultResponse.getData()).isNull();
        });
    }

    @Test
    void okResponseWithData() {
        String testData = "test data";

        ResultResponse<String> resultResponse = ResultResponse.<String>builder()
                .status(HttpStatus.CREATED.value())
                .message("test message")
                .data(testData)
                .build();
        resultResponse.setData(testData);

        ResponseEntity<ResultResponse<String>> responseEntity = ResultResponse.ok(resultResponse);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ResultResponse<String> result = responseEntity.getBody();

        assertSoftly(softAssertions -> {
            assertThat(result).isNotNull();
            assertThat(result.getTimestamp().getYear()).isEqualTo(LocalDateTime.now().getYear());
            assertThat(result.getStatus()).isEqualTo(HttpStatus.CREATED.value());
            assertThat(result.getData()).isEqualTo(testData);
        });
    }

    @Test
    void createdResponse() {
        URI testUri = URI.create("http://test.com");

        ResponseEntity responseEntity = ResultResponse.created(testUri);

        assertSoftly(softAssertions -> {
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(responseEntity.getBody()).isNull();
        });
    }
}