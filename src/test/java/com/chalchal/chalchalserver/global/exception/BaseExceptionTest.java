package com.chalchal.chalchalserver.global.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BaseExceptionTest {
    @Test
    void testErrorCode() {
        BaseException exception = BaseException.MENEBER_NOT_FOUND_EXCEPTION;
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.MEMBER_NOT_FOUND);
    }
}