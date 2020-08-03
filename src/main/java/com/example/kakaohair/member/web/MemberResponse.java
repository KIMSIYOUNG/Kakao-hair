package com.example.kakaohair.member.web;

import java.beans.ConstructorProperties;

import com.example.kakaohair.member.domain.Member;
import com.example.kakaohair.member.domain.MemberState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = {@ConstructorProperties({"id", "name", "memberState", "socialId" })})
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class MemberResponse {
    private final Long id;
    private final String name;
    private final MemberState memberState;
    private final String socialId;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
            .id(member.getId())
            .name(member.getName())
            .memberState(member.getMemberState())
            .socialId(member.getSocialId())
            .build();
    }
}
