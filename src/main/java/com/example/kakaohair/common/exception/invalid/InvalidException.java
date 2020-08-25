package com.example.kakaohair.common.exception.invalid;

import com.example.kakaohair.common.exception.BusinessException;
import com.example.kakaohair.common.exception.ErrorCode;

public class InvalidException extends BusinessException {
    public InvalidException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
}
