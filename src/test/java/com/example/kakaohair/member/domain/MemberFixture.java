package com.example.kakaohair.member.domain;

import java.time.LocalDateTime;

import com.example.kakaohair.member.application.MemberUpdateRequest;
import com.example.kakaohair.member.web.MemberCreateRequest;

public class MemberFixture {
    public static final String NAME = "TEST_NAME";
    public static final String UPDATE_NAME = "UPDATED_NAME";
    public static final long ID = 1L;

    public static MemberCreateRequest createDto() {
        return MemberCreateRequest.builder()
            .name(NAME)
            .build();
    }

    public static Member memberWithId() {
        return Member.builder()
            .id(ID)
            .createdAt(LocalDateTime.now().minusDays(3))
            .updatedAt(LocalDateTime.now())
            .name(NAME)
            .memberState(MemberState.ACTIVE)
            .build();
    }

    public static Member memberWithOutId() {
        return Member.builder()
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now().plusDays(3))
            .name(NAME)
            .memberState(MemberState.ACTIVE)
            .build();
    }

    public static Member updatedMember() {
        return Member.builder()
            .id(ID)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now().plusDays(3))
            .name(UPDATE_NAME)
            .memberState(MemberState.ACTIVE)
            .build();
    }

    public static MemberCreateRequest createWrongDto() {
        return MemberCreateRequest.builder().build();
    }

    public static MemberUpdateRequest updateDto() {
        return MemberUpdateRequest.builder()
            .name(UPDATE_NAME)
            .build();
    }

    public static MemberUpdateRequest updateWrongDto() {
        return MemberUpdateRequest.builder().build();
    }
}
