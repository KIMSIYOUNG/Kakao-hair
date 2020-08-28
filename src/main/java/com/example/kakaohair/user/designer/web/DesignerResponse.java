package com.example.kakaohair.user.designer.web;

import java.beans.ConstructorProperties;

import com.example.kakaohair.user.designer.Designer;
import com.example.kakaohair.user.member.domain.memberinfo.MemberInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = {@ConstructorProperties({"id", "name", "email","profile","phone"})})
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class DesignerResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String profile;
    private final String phone;

    public static DesignerResponse of(Designer designer) {
        MemberInfo info = designer.getMemberInfo();

        return DesignerResponse.builder()
            .id(designer.getId())
            .email(info.getEmail())
            .name(info.getName())
            .phone(info.getPhone())
            .profile(info.getProfile())
            .build();
    }
}
