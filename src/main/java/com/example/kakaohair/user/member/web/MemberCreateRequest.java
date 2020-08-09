package com.example.kakaohair.user.member.web;

import javax.validation.constraints.NotBlank;

import com.example.kakaohair.user.member.domain.Member;
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

    private String socialId;

    public Member toMember() {
        return Member.builder()
            .socialId(this.socialId)
            .name(this.name)
            .build();
    }
}
