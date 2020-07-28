package com.example.kakaohair.member.web;

import com.example.kakaohair.member.domain.Member;
import com.example.kakaohair.member.domain.MemberState;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponse {
    private final Long id;
    private final String name;
    private final MemberState memberState;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
            .id(member.getId())
            .name(member.getName())
            .memberState(member.getMemberState())
            .build();
    }
}
