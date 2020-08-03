package com.example.kakaohair.common.infra.kakao;

import java.beans.ConstructorProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @ConstructorProperties({"access_token", "token_type",
    "refresh_token", "expires_in", "refresh_token_expires_in", "scope"}))
@Builder
@Getter
public class KakaoTokenResponse {
    private final String accessToken;

    private final String tokenType;

    private final String refreshToken;

    private final int expiresIn;

    private final int refreshTokenExpiresIn;

    private final String scope;
}
