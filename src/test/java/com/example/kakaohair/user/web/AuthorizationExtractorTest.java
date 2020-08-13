package com.example.kakaohair.user.web;

import static org.assertj.core.api.Assertions.*;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockHttpServletRequest;

import com.example.kakaohair.common.exception.TokenInvalidException;
import com.example.kakaohair.common.infra.kakao.TokenResponse;

class AuthorizationExtractorTest {

    @DisplayName("정상적으로 토큰을 Extract한다.")
    @Test
    void extract() {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        String tokenType = "bearer ";
        String expectedToken = "TEST_TOKEN";
        servletRequest.addHeader(HttpHeaders.AUTHORIZATION, tokenType + expectedToken);
        AuthorizationExtractor extractor = new AuthorizationExtractor();

        TokenResponse token = extractor.extract(servletRequest);
        assertThat(token.getAccessToken()).isEqualTo(expectedToken);
    }

    @DisplayName("토큰에 들어있는 값이 Null인 경우 예외를 반환한다.")
    @Test
    void tokenNullInvalid() {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        AuthorizationExtractor extractor = new AuthorizationExtractor();

        assertThatThrownBy(() -> extractor.extract(servletRequest))
            .isInstanceOf(TokenInvalidException.class);
    }

    @DisplayName("Bearer 타입이 아닌 토큰은 예외를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"bearer length three","bearer", "notBearer length2"})
    void notBearerTypeToken(String value) {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.addHeader(HttpHeaders.AUTHORIZATION, value);
        AuthorizationExtractor extractor = new AuthorizationExtractor();

        assertThatThrownBy(() -> extractor.extract(servletRequest))
            .isInstanceOf(TokenInvalidException.class);
    }
}