package com.chalchal.chalchalserver.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    public final static BaseException MENEBER_NOT_FOUND_EXCEPTION = new BaseException(ErrorCode.MEMBER_NOT_FOUND);
    public final static BaseException AUTH_NOT_FOUND_EXCEPTION = new BaseException(ErrorCode.AUTH_NOT_FOUND);
    public final static BaseException AUTH_ALREADY_DONE_EXCEPTION = new BaseException(ErrorCode.AUTH_ALREADY_DONE);

    public final static BaseException RESOURCE_NOT_FOUND_EXCEPTION = new BaseException(ErrorCode.RESOURCE_NOT_FOUND);

    private final ErrorCode errorCode;
}
