package com.chalchal.chalchalserver.auth.dto;

import com.chalchal.chalchalserver.auth.domain.UserMailAuth;
import com.chalchal.chalchalserver.global.dto.Flag;
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
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import(LoginUserRequest.class)
class UserAuthRequestTest {

    @Autowired
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("UserAuthRequest 빈생성자 생성")
    void noArgsConstructor() {
        UserAuthRequest userAuthRequest = new UserAuthRequest();

        assertThat(userAuthRequest).isNotNull();
    }

    @Test
    @DisplayName("UserAuthRequest 생성")
    void userAuthRequest() {
        //given
        String svcNo = "svcNo";
        Long id = 0L;
        String email = "test@test.com";
        String code = "123456";
        Flag authYn = Flag.Y;
        LocalDateTime validDate = LocalDateTime.now();

        //when
        UserAuthRequest request = UserAuthRequest.builder()
                .svcNo(svcNo)
                .id(id)
                .email(email)
                .code(code)
                .authYn(authYn)
                .build();

        //then
        assertSoftly(softAssertions -> {
            assertThat(request.getSvcNo()).isEqualTo(svcNo);
            assertThat(request.getId()).isEqualTo(id);
            assertThat(request.getEmail()).isEqualTo(email);
            assertThat(request.getCode()).isEqualTo(code);
            assertThat(request.getAuthYn()).isEqualTo(authYn);
            assertThat(request.getValidDate().toLocalDate()).isEqualTo(UserMailAuth.setValidDate().toLocalDate());
        });
    }

    @Test
    @DisplayName("email 값이 비어있을 경우")
    void emailIsNull() {
        //given
        String svcNo = "svcNo";
        Long id = 0L;
        String email = null;
        String code = "123456";
        Flag authYn = Flag.Y;

        //when
        UserAuthRequest request = UserAuthRequest.builder()
                .svcNo(svcNo)
                .id(id)
                .email(email)
                .code(code)
                .authYn(authYn)
                .build();

        Set<ConstraintViolation<UserAuthRequest>> violations = validator.validate(request);

        //then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("email 형식이 맞지 않을 경우")
    void emailIsInvalid() {
        //given
        String svcNo = "svcNo";
        Long id = 0L;
        String email = "test";
        String code = "123456";
        Flag authYn = Flag.Y;

        //when
        UserAuthRequest request = UserAuthRequest.builder()
                .svcNo(svcNo)
                .id(id)
                .email(email)
                .code(code)
                .authYn(authYn)
                .build();

        Set<ConstraintViolation<UserAuthRequest>> violations = validator.validate(request);

        //then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("code 값이 비어있을 경우")
    void codeIsNull() {
        //given
        String svcNo = "svcNo";
        Long id = 0L;
        String email = "test@test.com";
        String code = null;
        Flag authYn = Flag.Y;

        //when
        UserAuthRequest request = UserAuthRequest.builder()
                .svcNo(svcNo)
                .id(id)
                .email(email)
                .code(code)
                .authYn(authYn)
                .build();

        Set<ConstraintViolation<UserAuthRequest>> violations = validator.validate(request);

        //then
        assertThat(violations.size()).isEqualTo(1);
    }
}