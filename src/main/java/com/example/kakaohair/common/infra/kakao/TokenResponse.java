package com.example.kakaohair.common.infra.kakao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TokenResponse {
    private final String token;

    public static TokenResponse of(String token) {
        return new TokenResponse(token);
    }
}
