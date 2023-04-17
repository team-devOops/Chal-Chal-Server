package com.chalchal.chalchalserver.auth.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import(UserRequest.class)
class UserRequestTest {

    @Autowired
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("UserRequest 빈생성자")
    void userRequestNoArgsConstructor() {
        UserRequest request = new UserRequest();

        assertThat(request).isNotNull();
    }

    @Test
    @DisplayName("UserRequest 생성")
    void userRequest() {
        String email = "email@test.com";
        String password = "12345678";
        String userId = "userId";
        String nickname = "nickname";

        UserRequest request = UserRequest.builder()
                .email(email)
                .password(password)
                .userId(userId)
                .nickName(nickname)
                .build();

        assertSoftly(softAssertions -> {
            assertThat(request.getEmail()).isEqualTo(email);
            assertThat(request.getPassword()).isEqualTo(password);
            assertThat(request.getUserId()).isEqualTo(userId);
            assertThat(request.getNickName()).isEqualTo(nickname);
        });
    }

    @Test
    @DisplayName("이메일이 null일 경우 유효성 검사 실패")
    void emailIsNull() {
        // given
        String email = null;
        String password = "12345678";
        String userId = "userId";
        String nickname = "nickname";

        UserRequest request = UserRequest.builder()
                .email(email)
                .password(password)
                .userId(userId)
                .nickName(nickname)
                .build();

        // when
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("이메일 형식이 맞지 않을 경우 유효성 검사 실패")
    void emailIsInvalid() {
        // given
        String email = "email";
        String password = "12345678";
        String userId = "userId";
        String nickname = "nickname";

        UserRequest request = UserRequest.builder()
                .email(email)
                .password(password)
                .userId(userId)
                .nickName(nickname)
                .build();

        // when
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("비밀번호가 null일 경우 유효성 검사 실패")
    void passwordIsNull() {
        // given
        String email = "test@test.com";
        String password = null;
        String userId = "userId";
        String nickname = "nickname";

        UserRequest request = UserRequest.builder()
                .email(email)
                .password(password)
                .userId(userId)
                .nickName(nickname)
                .build();

        // when
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("비밀번호가 범위 밖인 경우 유효성 검사 실패")
    void passwordIsInvalid() {
        // given
        String email = "test@test.com";
        String password = "123";
        String userId = "userId";
        String nickname = "nickname";

        UserRequest request = UserRequest.builder()
                .email(email)
                .password(password)
                .userId(userId)
                .nickName(nickname)
                .build();

        // when
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("userId가 null일 경우 유효성 검사 실패")
    void userIdIsNull() {
        // given
        String email = "test@test.com";
        String password = "12345678";
        String userId = null;
        String nickname = "nickname";

        UserRequest request = UserRequest.builder()
                .email(email)
                .password(password)
                .userId(userId)
                .nickName(nickname)
                .build();

        // when
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("닉네임이 null일 경우 유효성 검사 실패")
    void nicknameIsNull() {
        // given
        String email = "test@test.com";
        String password = "12345678";
        String userId = "userId";
        String nickname = null;

        UserRequest request = UserRequest.builder()
                .email(email)
                .password(password)
                .userId(userId)
                .nickName(nickname)
                .build();

        // when
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }
}