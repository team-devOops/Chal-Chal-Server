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
@Import(LoginUserRequest.class)
class LoginUserRequestTest {

    @Autowired
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("LoginUserRequest 생성")
    void loginUserRequest() {
        String email = "test@test.com";
        String password = "12345678";

        LoginUserRequest request = LoginUserRequest.builder()
                .email(email)
                .password(password)
                .build();

        assertSoftly(softAssertions -> {
            assertThat(request).isNotNull();
            assertThat(request.getEmail()).isEqualTo(email);
            assertThat(request.getPassword()).isEqualTo(password);
        });
    }

    @Test
    @DisplayName("이메일이 null일 경우 유효성 검사 실패")
    void testNullEmail() {
        // given
        LoginUserRequest request = LoginUserRequest.builder()
                .email(null)
                .password("password123")
                .build();

        // when
        Set<ConstraintViolation<LoginUserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("패스워드가 null일 경우 유효성 검사 실패")
    void testNullPassword() {
        // given
        LoginUserRequest request = LoginUserRequest.builder()
                .email("test@test.com")
                .password(null)
                .build();

        // when
        Set<ConstraintViolation<LoginUserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("패스워드가 유효한 범위 내일 경우 유효성 검사 통과")
    void testValidPassword() {
        // given
        LoginUserRequest request = LoginUserRequest.builder()
                .email("test@test.com")
                .password("password123")
                .build();

        // when
        Set<ConstraintViolation<LoginUserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("패스워드가 유효한 범위를 벗어날 경우 유효성 검사 실패")
    void testInvalidPassword() {
        // given
        LoginUserRequest request = LoginUserRequest.builder()
                .email("test@test.com")
                .password("1234")
                .build();

        // when
        Set<ConstraintViolation<LoginUserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("패스워드 이메일 모두 유효성 검사 실패")
    void testInvalidEmailAndPassword() {
        // given
        LoginUserRequest request = LoginUserRequest.builder()
                .email("test")
                .password("1234")
                .build();

        // when
        Set<ConstraintViolation<LoginUserRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(2);
    }
}