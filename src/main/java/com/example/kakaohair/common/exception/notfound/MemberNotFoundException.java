package com.example.kakaohair.common.exception.notfound;

import com.example.kakaohair.common.exception.ErrorCode;

public class MemberNotFoundException extends NotFoundException {

    public MemberNotFoundException(Long id) {
        super(String.format("Member(member id = %d not exist)", id),
            ErrorCode.MEMBER_NOT_FOUND);
    }

    public MemberNotFoundException(String id) {
        super(String.format("Member(member id = %s not exist)", id),
            ErrorCode.MEMBER_NOT_FOUND);
    }
}
