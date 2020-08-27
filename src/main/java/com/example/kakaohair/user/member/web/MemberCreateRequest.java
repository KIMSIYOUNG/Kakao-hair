package com.example.kakaohair.user.member.web;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    @Valid @NotNull
    private MemberInfo memberInfo;

    @Valid @NotNull
    private HairInfo hairInfo;

    public Member toMember() {
        return Member.builder()
            .memberInfo(memberInfo)
            .hairInfo(hairInfo)
            .build();
    }
}
