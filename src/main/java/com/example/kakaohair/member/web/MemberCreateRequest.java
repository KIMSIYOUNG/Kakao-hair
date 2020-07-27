package com.example.kakaohair.member.web;

import com.example.kakaohair.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberCreateRequest {
    private String name;

    public Member toMember() {
        return new Member(this.name);
    }
}
