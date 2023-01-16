package com.chalchal.chalchalserver.global.exception;

import com.chalchal.chalchalserver.global.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { BaseException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(BaseException e) {
        log.error("handleBaseException throw BaseException : {}", e.getErrorCode());
        return ErrorResponse.toErrorResponse(e.getErrorCode());
    }
}
