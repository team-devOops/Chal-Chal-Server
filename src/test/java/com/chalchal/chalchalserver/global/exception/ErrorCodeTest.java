package com.chalchal.chalchalserver.global.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ErrorCodeTest {
    @Test
    void badRequest() {
        assertEquals(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_REFRESH_TOKEN.getHttpStatus());
        assertThat(ErrorCode.INVALID_REFRESH_TOKEN.getDetail()).isEqualTo("리프레시 토큰이 유효하지 않습니다");

        assertEquals(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_INPUT.getHttpStatus());
        assertThat(ErrorCode.INVALID_INPUT.getDetail()).isEqualTo("잘못된 입력값입니다.");

        assertEquals(HttpStatus.BAD_REQUEST, ErrorCode.AUTH_ALREADY_DONE.getHttpStatus());
        assertThat(ErrorCode.AUTH_ALREADY_DONE.getDetail()).isEqualTo("이미 인증 완료한 유저");

        assertEquals(HttpStatus.BAD_REQUEST, ErrorCode.AUTH_NUM_IS_NOT_COMPARE.getHttpStatus());
        assertThat(ErrorCode.AUTH_NUM_IS_NOT_COMPARE.getDetail()).isEqualTo("잘못된 인증번호");
    }

    @Test
    void unauthorized() {
        assertEquals(HttpStatus.UNAUTHORIZED, ErrorCode.NOT_AUTHORIZED.getHttpStatus());
        assertThat(ErrorCode.NOT_AUTHORIZED.getDetail()).isEqualTo("아직 인증되지 않았습니다");
    }

    @Test
    void notFound() {
        assertEquals(HttpStatus.NOT_FOUND, ErrorCode.MEMBER_NOT_FOUND.getHttpStatus());
        assertThat(ErrorCode.MEMBER_NOT_FOUND.getDetail()).isEqualTo("해당 유저 정보를 찾을 수 없습니다");

        assertEquals(HttpStatus.NOT_FOUND, ErrorCode.AUTH_NOT_FOUND.getHttpStatus());
        assertThat(ErrorCode.AUTH_NOT_FOUND.getDetail()).isEqualTo("유효한 인증 내역이 없습니다");

        assertEquals(HttpStatus.NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND.getHttpStatus());
        assertThat(ErrorCode.RESOURCE_NOT_FOUND.getDetail()).isEqualTo("데이터가 없습니다");
    }

    @Test
    void conflict() {
        assertEquals(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_RESOURCE.getHttpStatus());
        assertThat(ErrorCode.DUPLICATE_RESOURCE.getDetail()).isEqualTo("데이터가 이미 존재합니다");
    }
}