package com.chalchal.chalchalserver.auth.dto;

import com.chalchal.chalchalserver.auth.domain.User;
import com.chalchal.chalchalserver.auth.domain.UserProfileImg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class UserResponseTest {

    @Test
    @DisplayName("UserResponse 빈생성자 생성")
    void noArgsConstructor() {
        UserResponse userResponse = new UserResponse();

        assertThat(userResponse).isNotNull();
    }

    @Test
    @DisplayName("UserResponse 생성")
    void userResponse() {
        //given
        String email = "email";
        String userId = "userId";
        String nickname = "nickname";
        String url = "url";

        //when
        UserResponse response = UserResponse.builder()
                .email(email)
                .userId(userId)
                .nickname(nickname)
                .url(url)
                .build();

        //then
        assertSoftly(softAssertions -> {
            assertThat(response.getEmail()).isEqualTo(email);
            assertThat(response.getUserId()).isEqualTo(userId);
            assertThat(response.getNickname()).isEqualTo(nickname);
            assertThat(response.getUrl()).isEqualTo(url);
        });
    }

    @Test
    @DisplayName("UserResponse user랑 userProfileImg으로 from")
    void fromByUserProfileImg() {
        //given
        String email = "email";
        String userId = "userId";
        String nickname = "nickname";

        User user = User.builder()
                .email(email)
                .userId(userId)
                .nickname(nickname)
                .build();

        String url = "url";

        UserProfileImg userProfileImg = UserProfileImg.builder()
                .url(url)
                .build();

        //when
        UserResponse response = UserResponse.from(user, userProfileImg);

        //then
        assertSoftly(softAssertions -> {
            assertThat(response.getEmail()).isEqualTo(email);
            assertThat(response.getUserId()).isEqualTo(userId);
            assertThat(response.getNickname()).isEqualTo(nickname);
            assertThat(response.getUrl()).isEqualTo(url);
        });
    }

    @Test
    @DisplayName("UserResponse user랑 userProfileImg으로 from")
    void fromByUser() {
        //given
        String email = "email";
        String userId = "userId";
        String nickname = "nickname";

        User user = User.builder()
                .email(email)
                .userId(userId)
                .nickname(nickname)
                .build();

        //when
        UserResponse response = UserResponse.from(user);

        //then
        assertSoftly(softAssertions -> {
            assertThat(response.getEmail()).isEqualTo(email);
            assertThat(response.getUserId()).isEqualTo(userId);
            assertThat(response.getNickname()).isEqualTo(nickname);
            assertThat(response.getUrl()).isNull();
        });
    }
}