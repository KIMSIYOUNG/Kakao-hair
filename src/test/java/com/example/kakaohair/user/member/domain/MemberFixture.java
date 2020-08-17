package com.example.kakaohair.user.member.domain;

import com.example.kakaohair.user.member.SocialInfo;
import com.example.kakaohair.user.member.domain.hairinfo.HairInfo;
import com.example.kakaohair.user.member.domain.memberinfo.MemberInfo;
import com.example.kakaohair.user.member.web.MemberCreateRequest;
import com.example.kakaohair.user.member.web.MemberUpdateRequest;

public class MemberFixture {
    public static final String NAME = "TEST_NAME";
    public static final String EMAIL = "TEST_NAME@TEST_EMAIL";
    public static final String UPDATE_NAME = "UPDATED_NAME";
    public static final long ID = 1L;
    public static final String SOCIAL_ID = "KAKAO-3214";

    private static MemberInfo createMemberInfo() {
        return MemberInfo.of(NAME, SOCIAL_ID, EMAIL, null, null, null, null);
    }

    private static HairInfo createHairInfo() {
        return HairInfo.init();
    }

    public static MemberCreateRequest createDto() {
        return MemberCreateRequest.builder()
            .memberInfo(createMemberInfo())
            .hairInfo(createHairInfo())
            .build();
    }

    public static Member memberWithId() {
        return Member.builder()
            .id(ID)
            .memberInfo(createMemberInfo())
            .hairInfo(createHairInfo())
            .build();
    }

    public static Member memberWithOutId() {
        return Member.builder()
            .memberInfo(createMemberInfo())
            .hairInfo(createHairInfo())
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

    public static SocialInfo socialInfo() {
        return SocialInfo.builder()
            .id(SOCIAL_ID)
            .email(EMAIL)
            .name(NAME)
            .build();
    }
}
