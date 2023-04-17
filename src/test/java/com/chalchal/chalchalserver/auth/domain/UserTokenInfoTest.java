package com.chalchal.chalchalserver.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class UserTokenInfoTest {

    @Test
    @DisplayName("UserTokenInfo 빈생성자")
    void userTokenInfoNoArgsConstructor() {
        UserTokenInfo userTokenInfo = new UserTokenInfo();

        assertThat(userTokenInfo).isNotNull();
    }

    @Test
    @DisplayName("UserTokenInfo 생성")
    void userTokenInfo() {
        //given
        Long tokenIndex = 1L;
        Long id = 1L;
        String refreshToken = "refreshToken";

        //when
        UserTokenInfo userTokenInfo = UserTokenInfo.builder()
                .tokenIndex(tokenIndex)
                .id(id)
                .refreshToken(refreshToken)
                .build();

        //then
        assertSoftly(softAssertions -> {
            assertThat(userTokenInfo.getTokenIndex()).isEqualTo(tokenIndex);
            assertThat(userTokenInfo.getId()).isEqualTo(id);
            assertThat(userTokenInfo.getRefreshToken()).isEqualTo(refreshToken);
        });
    }
}