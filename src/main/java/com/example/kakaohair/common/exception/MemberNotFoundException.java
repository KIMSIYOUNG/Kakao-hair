package com.example.kakaohair.common.exception;

public class MemberNotFoundException extends NotFoundException {

    public MemberNotFoundException(Long id) {
        super(String.format("Member(member id = %d not exist)", id),
            ErrorCode.MEMBER_NOT_FOUND);
    }
}
