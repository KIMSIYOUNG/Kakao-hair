package com.example.kakaohair.common.exception.notfound;

import com.example.kakaohair.common.exception.BusinessException;
import com.example.kakaohair.common.exception.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
}
