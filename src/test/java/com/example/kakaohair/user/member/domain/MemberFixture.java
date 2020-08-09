package com.example.kakaohair.user.member.domain;

import java.time.LocalDateTime;

import com.example.kakaohair.user.member.SocialInfo;
import com.example.kakaohair.user.member.web.MemberCreateRequest;
import com.example.kakaohair.user.member.web.MemberUpdateRequest;

public class MemberFixture {
    public static final String NAME = "TEST_NAME";
    public static final String EMAIL = "TEST_NAME@TEST_EMAIL";
    public static final String UPDATE_NAME = "UPDATED_NAME";
    public static final long ID = 1L;
    public static final String SOCIAL_ID = "KAKAO-3214";

    public static MemberCreateRequest createDto() {
        return MemberCreateRequest.builder()
            .name(NAME)
            .socialId(SOCIAL_ID)
            .build();
    }

    public static Member memberWithId() {
        return Member.builder()
            .id(ID)
            .socialId(SOCIAL_ID)
            .createdAt(LocalDateTime.now().minusDays(3))
            .updatedAt(LocalDateTime.now())
            .name(NAME)
            .build();
    }

    public static Member memberWithOutId() {
        return Member.builder()
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now().plusDays(3))
            .socialId(SOCIAL_ID)
            .name(NAME)
            .build();
    }

    public static Member updatedMember() {
        return Member.builder()
            .id(ID)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now().plusDays(3))
            .name(UPDATE_NAME)
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

    public static Member socialMember() {
        return memberWithId().toBuilder()
            .socialId(SOCIAL_ID)
            .build();
    }

    public static SocialInfo socialInfo() {
        return SocialInfo.builder()
            .id(SOCIAL_ID)
            .email(EMAIL)
            .name(NAME)
            .build();
    }
}
