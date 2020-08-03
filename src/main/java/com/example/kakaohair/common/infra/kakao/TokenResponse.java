package com.example.kakaohair.common.infra.kakao;

import java.beans.ConstructorProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @ConstructorProperties("accessToken"))
@Getter
public class TokenResponse {
    private final String accessToken;

    public static TokenResponse of(String token) {
        return new TokenResponse(token);
    }

    public static TokenResponse of(KakaoTokenResponse tokenResponse) {
        return new TokenResponse(tokenResponse.getAccessToken());
    }
}
