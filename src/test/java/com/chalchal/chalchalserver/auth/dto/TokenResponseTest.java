package com.chalchal.chalchalserver.auth.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class TokenResponseTest {

    @Test
    @DisplayName("tokenResponse 빈생성자")
    void tokenResponseNoArgsConstructor() {
        TokenResponse response = new TokenResponse();

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("tokenResponse 생성")
    void tokenResponse() {
        //given
        long index = 0L;
        long id = 11L;
        long refreshTokenIndex = 1L;

        String accessToken = "accessToken";
        String refreshToken = "refreshToken";

        //when
        TokenResponse response = TokenResponse.builder()
                .index(index)
                .id(id)
                .refreshTokenIndex(refreshTokenIndex)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        //then
        assertSoftly(softAssertions -> {
            assertThat(response.getIndex()).isEqualTo(index);
            assertThat(response.getId()).isEqualTo(id);
            assertThat(response.getRefreshTokenIndex()).isEqualTo(refreshTokenIndex);
            assertThat(response.getAccessToken()).isEqualTo(accessToken);
            assertThat(response.getRefreshToken()).isEqualTo(refreshToken);
        });
    }
}