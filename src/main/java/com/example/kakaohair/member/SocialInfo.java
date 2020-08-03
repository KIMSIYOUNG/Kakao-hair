package com.example.kakaohair.member;

import com.example.kakaohair.common.infra.kakao.KakaoUserResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SocialInfo {

    private String id;

    private String name;

    private String email;

    public static SocialInfo from(final KakaoUserResponse kakaoUserResponse) {
        return SocialInfo.builder()
            .id(String.valueOf(kakaoUserResponse.getId()))
            .email(kakaoUserResponse.getEmail())
            .name(kakaoUserResponse.getNickname())
            .build();
    }
}
