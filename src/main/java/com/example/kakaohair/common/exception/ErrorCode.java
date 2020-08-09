package com.example.kakaohair.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Validation-001"),

    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "TOKEN-001"),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Member-001");

    private final HttpStatus status;
    private final String code;
}
