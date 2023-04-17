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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import(UserAuthMailRequest.class)
class UserAuthMailRequestTest {
    @Autowired
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("userAuthMail 빈생성자")
    void userAuthMailRequestNoArgsConstructor() {
        UserAuthMailRequest request = new UserAuthMailRequest();

        assertThat(request).isNotNull();
    }

    @Test
    @DisplayName("userAuthMail 생성")
    void userAuthMailRequest() {
        //given
        String email = "email@test.com";

        //when
        UserAuthMailRequest request = UserAuthMailRequest.builder()
                .email(email)
                .build();

        //then
        assertThat(request.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("이메일이 null일 경우 유효성 검사 실패")
    void emailIsNull() {
        // given
        String email = null;

        UserAuthMailRequest request = UserAuthMailRequest.builder()
                .email(email)
                .build();

        // when
        Set<ConstraintViolation<UserAuthMailRequest>> violations = validator.validate(request);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }
}