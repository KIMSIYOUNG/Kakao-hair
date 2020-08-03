package com.example.kakaohair.common.exception;

public class TokenInvalidException extends BusinessException {
    public TokenInvalidException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
}
