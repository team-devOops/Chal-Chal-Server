package com.chalchal.chalchalserver.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class UserProfileImgTest {

    @Test
    @DisplayName("UserProfileImg 빈생성자")
    void noArgsConstructor() {
        UserProfileImg userProfileImg = new UserProfileImg();

        assertThat(userProfileImg).isNotNull();
    }

    @Test
    @DisplayName("UserProfileImg 생성")
    void userProfileImg() {
        //given
        String svcNo = "svcNo";
        Long id = 0L;
        int orderSeq = 1;
        String url = "url";

        //when
        UserProfileImg userProfileImg = UserProfileImg.builder()
                .svcNo(svcNo)
                .id(id)
                .orderSeq(orderSeq)
                .url(url)
                .build();

        //then
        assertSoftly(softAssertions -> {
            assertThat(userProfileImg.getSvcNo()).isEqualTo(svcNo);
            assertThat(userProfileImg.getId()).isEqualTo(id);
            assertThat(userProfileImg.getOrderSeq()).isEqualTo(orderSeq);
            assertThat(userProfileImg.getUrl()).isEqualTo(url);
        });
    }
}