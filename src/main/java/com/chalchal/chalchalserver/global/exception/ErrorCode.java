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

    AUTH_ALREADY_DONE(BAD_REQUEST, "이미 인증 완료한 유저"),
    AUTH_NUM_IS_NOT_COMPARE(BAD_REQUEST, "잘못된 인증번호"),
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    AUTH_NOT_FOUND(NOT_FOUND, "유효한 인증 내역이 없습니다"),
    RESOURCE_NOT_FOUND(NOT_FOUND, "데이터가 없습니다"),

    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다");

    private final HttpStatus httpStatus;
    private final String detail;
}
