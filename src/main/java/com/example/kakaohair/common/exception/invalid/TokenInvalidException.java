package com.example.kakaohair.common.exception.invalid;

import com.example.kakaohair.common.exception.BusinessException;
import com.example.kakaohair.common.exception.ErrorCode;

public class TokenInvalidException extends BusinessException {
    public TokenInvalidException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
}
