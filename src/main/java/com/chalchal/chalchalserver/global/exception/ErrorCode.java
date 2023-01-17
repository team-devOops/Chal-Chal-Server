package com.chalchal.chalchalserver.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    INVALID_INPUT(BAD_REQUEST, "잘못된 입력값입니다."),
    NOT_AUTHORIZED(UNAUTHORIZED, "아직 인증되지 않았습니다"),

    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),

    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다");

    private final HttpStatus httpStatus;
    private final String detail;
}
