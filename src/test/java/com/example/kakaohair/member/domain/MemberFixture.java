package com.example.kakaohair.member.domain;

import java.time.LocalDateTime;

import com.example.kakaohair.member.web.MemberCreateRequest;

public class MemberFixture {
    public static final String NAME = "TEST_NAME";
    
    public static MemberCreateRequest createDto() {
        return MemberCreateRequest.builder()
            .name(NAME)
            .build();
    }

    public static Member memberWithId() {
        return Member.builder()
            .id(1L)
            .createdAt(LocalDateTime.now().minusDays(3))
            .updatedAt(LocalDateTime.now())
            .name(NAME)
            .build();
    }

    public static Member memberWithOutId() {
        return Member.builder()
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now().plusDays(3))
            .name(NAME)
            .build();
    }
}
