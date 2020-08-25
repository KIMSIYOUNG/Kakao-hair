package com.example.kakaohair.common.exception.invalid;

import com.example.kakaohair.common.exception.ErrorCode;

public class RequestInvalidException extends InvalidException {
    public RequestInvalidException(String input) {
        super(String.format("%s는 잘못된 입력값입니다.", input), ErrorCode.INVALID_REQUEST);
    }
}
