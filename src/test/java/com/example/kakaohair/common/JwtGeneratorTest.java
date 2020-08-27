package com.example.kakaohair.common;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.kakaohair.common.exception.invalid.TokenInvalidException;
import com.example.kakaohair.common.infra.kakao.TokenResponse;

class JwtGeneratorTest {
    private static final String SOCIAL_ID = "SOCIAL-KAKAO-EXAMPLE";

    private JwtGenerator jwtGenerator;

    @BeforeEach
    void setUp() {
        jwtGenerator = new JwtGenerator("secret", 1000L);
    }

    @DisplayName("토큰을 정상적으로 생성합니다.")
    @Test
    void create() {
        final TokenResponse customToken = jwtGenerator.createCustomToken(SOCIAL_ID);

        assertAll(
            () -> assertThat(customToken.getAccessToken()).isInstanceOf(String.class),
            () -> assertThat(customToken.getAccessToken().split("\\.").length).isEqualTo(3)
        );
    }

    @DisplayName("생성한 토큰을 정상적으로 해독합니다.")
    @Test
    void getSubject() {
        final TokenResponse customToken = jwtGenerator.createCustomToken(SOCIAL_ID);
        final String subject = jwtGenerator.getSubject(customToken.getAccessToken());

        assertThat(subject).isEqualTo(SOCIAL_ID);
    }

    @DisplayName("토큰이 만료된 경우 예외를 반환합니다.")
    @Test
    void tokenExpired() throws InterruptedException {
        final TokenResponse customToken = jwtGenerator.createCustomToken(SOCIAL_ID);
        Thread.sleep(1001L);

        assertThatThrownBy(() -> jwtGenerator.getSubject(customToken.getAccessToken()))
            .isInstanceOf(TokenInvalidException.class)
            .hasMessage("토큰이 만료되었습니다. 다시 로그인 해주세요.");
    }
}