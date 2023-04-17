package com.chalchal.chalchalserver.auth.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class AuthNumCompareRequestTest {
    @Test
    void authNumCompareRequest() {
        Long id = 90L;

        AuthNumCompareRequest authNumCompareRequest = new AuthNumCompareRequest(id);

        assertSoftly(softAssertions -> {
            assertThat(authNumCompareRequest).isNotNull();
            assertThat(authNumCompareRequest.getId()).isEqualTo(id);
        });
    }
}