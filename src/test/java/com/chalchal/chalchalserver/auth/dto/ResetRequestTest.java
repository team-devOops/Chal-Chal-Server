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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import(ResetRequest.class)
class ResetRequestTest {

    @Autowired
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("ResetRequest 생성")
    void resetRequestAllArgs() {
        //given
        String password = "12345678";
        String checkPassword = "12345678";

        //when
        ResetRequest resetRequest = ResetRequest.builder()
                .password(password)
                .checkPassword(checkPassword)
                .build();

        assertSoftly(softAssertions -> {
            assertThat(resetRequest).isNotNull();
            assertThat(resetRequest.getPassword()).isEqualTo(password);
            assertThat(resetRequest.getCheckPassword()).isEqualTo(checkPassword);
        });
    }

    @Test
    @DisplayName("ResetRequest 빈 생성자")
    void resetRequestNoArgs() {
        //given
        ResetRequest resetRequest = new ResetRequest();

        assertThat(resetRequest).isNotNull();
    }

    @Test
    @DisplayName("비밀번호가 null일 경우 유효성 검사 실패")
    void nullPassword() {
        // given
        String password = null;
        String checkPassword = "12345678";

        ResetRequest resetRequest = ResetRequest.builder()
                .password(password)
                .checkPassword(checkPassword)
                .build();

        // when
        Set<ConstraintViolation<ResetRequest>> violations = validator.validate(resetRequest);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("체크 비밀번호가 null일 경우 유효성 검사 실패")
    void nullPasswordCheck() {
        // given
        String password = "12345678";
        String checkPassword = null;

        ResetRequest resetRequest = ResetRequest.builder()
                .password(password)
                .checkPassword(checkPassword)
                .build();

        // when
        Set<ConstraintViolation<ResetRequest>> violations = validator.validate(resetRequest);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("패스워드가 유효한 범위를 벗어날 경우 유효성 검사 실패")
    void invalidPassword() {
        // given
        String password = "12345";
        String checkPassword = "12345678";

        ResetRequest resetRequest = ResetRequest.builder()
                .password(password)
                .checkPassword(checkPassword)
                .build();

        // when
        Set<ConstraintViolation<ResetRequest>> violations = validator.validate(resetRequest);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("체크 패스워드가 유효한 범위를 벗어날 경우 유효성 검사 실패")
    void invalidCheckPassword() {
        // given
        String password = "12345678";
        String checkPassword = "12345";

        ResetRequest resetRequest = ResetRequest.builder()
                .password(password)
                .checkPassword(checkPassword)
                .build();

        // when
        Set<ConstraintViolation<ResetRequest>> violations = validator.validate(resetRequest);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("유효성 검사 통과")
    void validResetRequest() {
        // given
        String password = "12345678";
        String checkPassword = "12345678";

        ResetRequest resetRequest = ResetRequest.builder()
                .password(password)
                .checkPassword(checkPassword)
                .build();

        // when
        Set<ConstraintViolation<ResetRequest>> violations = validator.validate(resetRequest);

        // then
        assertThat(violations).isEmpty();
    }
}