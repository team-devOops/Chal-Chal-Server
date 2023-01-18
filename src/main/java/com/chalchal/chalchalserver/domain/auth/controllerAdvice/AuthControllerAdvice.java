package com.chalchal.chalchalserver.domain.auth.controllerAdvice;

import com.chalchal.chalchalserver.global.dto.ErrorResponse;
import com.chalchal.chalchalserver.global.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredentialsExceptionHandler(BadCredentialsException e) {
        return ErrorResponse.toErrorResponse(ErrorCode.NOT_AUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return ErrorResponse.toErrorResponse(ErrorCode.INVALID_INPUT);
    }
}
