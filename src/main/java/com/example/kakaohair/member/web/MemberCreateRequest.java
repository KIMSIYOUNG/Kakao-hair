package com.example.kakaohair.member.web;

import javax.validation.constraints.NotBlank;

import com.example.kakaohair.member.domain.Member;
import com.example.kakaohair.member.domain.MemberState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberCreateRequest {
    @NotBlank
    private String name;

    public Member toMember() {
        return Member.builder()
            .name(this.name)
            .memberState(MemberState.ACTIVE)
            .build();
    }
}
