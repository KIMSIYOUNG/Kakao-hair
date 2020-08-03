package com.example.kakaohair.member;

import java.beans.ConstructorProperties;

import com.example.kakaohair.common.infra.kakao.KakaoUserResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @ConstructorProperties({"id", "name", "email"}))
@Builder
@Getter
public class SocialInfo {

    private final String id;

    private final String name;

    private final String email;

    public static SocialInfo from(final KakaoUserResponse kakaoUserResponse) {
        return SocialInfo.builder()
            .id(String.valueOf(kakaoUserResponse.getId()))
            .email(kakaoUserResponse.getEmail())
            .name(kakaoUserResponse.getNickname())
            .build();
    }
}
