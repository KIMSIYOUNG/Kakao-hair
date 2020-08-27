package com.example.kakaohair.common.exception.notfound;

import com.example.kakaohair.common.exception.ErrorCode;

public class OwnerNotFoundException extends NotFoundException {

    public OwnerNotFoundException(Long id) {
        super(String.format("Owner(owner id = %d not exist)", id), ErrorCode.OWNER_NOT_FOUND);
    }
}
