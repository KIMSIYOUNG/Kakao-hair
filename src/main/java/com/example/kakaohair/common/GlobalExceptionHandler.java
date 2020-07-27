package com.example.kakaohair.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponse> requestValidException(MethodArgumentNotValidException exception) {
        log.error("MethodArgumentNotValidException", exception);

        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        final ErrorResponse response = ErrorResponse.of(errorCode.getStatus(), errorCode.getCode(),
            "Invalid request data", exception.getBindingResult());

        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<ErrorResponse> business(BusinessException exception) {
        log.error("BusinessException", exception);

        ErrorCode errorCode = exception.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode.getStatus(), errorCode.getCode(),
            exception.getMessage());

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
}
