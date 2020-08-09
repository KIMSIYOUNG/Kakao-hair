package com.example.kakaohair.user.member.web;

import java.beans.ConstructorProperties;

import com.example.kakaohair.user.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = {@ConstructorProperties({"id", "name", "socialId" })})
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class MemberResponse {
    private final Long id;
    private final String name;
    private final String socialId;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
            .id(member.getId())
            .name(member.getName())
            .socialId(member.getSocialId())
            .build();
    }
}
