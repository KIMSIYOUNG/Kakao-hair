package com.example.kakaohair.user.member.web;

import com.example.kakaohair.user.member.domain.Member;
import com.example.kakaohair.user.member.domain.hairinfo.HairInfo;
import com.example.kakaohair.user.member.domain.memberinfo.MemberInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberCreateRequest {
    private MemberInfo memberInfo;

    private HairInfo hairInfo;

    public Member toMember() {
        return Member.builder()
            .memberInfo(memberInfo)
            .hairInfo(hairInfo)
            .build();
    }
}
