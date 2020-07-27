package com.example.kakaohair.common;

public class NotFoundException extends BusinessException {

    public NotFoundException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
}
