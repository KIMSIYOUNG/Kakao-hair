package com.example.kakaohair.member.domain;

import com.example.kakaohair.member.web.MemberCreateRequest;

public class MemberFixture {
    public static final String NAME = "TEST_NAME";
    
    public static MemberCreateRequest createDto() {
        return MemberCreateRequest.builder()
            .name(NAME)
            .build();
    }
}
