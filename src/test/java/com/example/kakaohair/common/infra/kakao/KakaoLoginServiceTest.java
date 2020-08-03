package com.example.kakaohair.common.infra.kakao;

import static com.example.kakaohair.common.LoginFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.example.kakaohair.common.JwtGenerator;
import com.example.kakaohair.member.SocialInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KakaoLoginServiceTest {
    private KakaoLoginService kakaoLoginService;

    @MockBean
    private JwtGenerator jwtGenerator;

    private MockWebServer mockServer;

    private String mockServerUrl;

    @Autowired
    private ObjectMapper objectMapper;

    private MockResponse tokenResponse;
    private MockResponse userResponse;

    @BeforeEach
    void setUp() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();
        mockServerUrl = mockServer.url("/").toString();
        kakaoLoginService = new KakaoLoginService(
            mockServerUrl, mockServerUrl, SERVER_URI, CLIENT_ID_VALUE,
            CLIENT_SECRET_VALUE, RESPONSE_TYPE_VALUE, GRANT_TYPE_VALUE);
        SetMockEnv();
    }

    private void SetMockEnv() throws JsonProcessingException {
        tokenResponse = new MockResponse()
            .addHeader("Content-Type", "application/json;charset=UTF-8")
            .setResponseCode(200)
            .setBody(objectMapper.writeValueAsString(mockKakaoTokenResponse()));

        userResponse = new MockResponse()
            .addHeader("Content-Type", "application/json;charset=UTF-8")
            .setResponseCode(200)
            .setBody(objectMapper.writeValueAsString(mockKakaoUserResponse()));

        final Dispatcher dispatcher = new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(RecordedRequest request) {
                if (request.getPath().contains(OAUTH_TOKEN_PATH)) {
                    return tokenResponse;
                }
                if (request.getPath().contains(USER_INFO_PATH)) {
                    return userResponse;
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        mockServer.setDispatcher(dispatcher);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockServer.shutdown();
    }

    @Test
    void getCode() {
        final String expectedUri = new DefaultUriBuilderFactory().builder()
            .path(mockServerUrl + AUTHORIZE_PATH)
            .queryParam(RESPONSE_TYPE, RESPONSE_TYPE_VALUE)
            .queryParam(CLIENT_ID, CLIENT_ID_VALUE)
            .queryParam(REDIRECT_URI, SERVER_URI + REDIRECT_PATH)
            .build()
            .toString();

        assertThat(kakaoLoginService.getCodeUrl()).isEqualTo(expectedUri);
    }

    @DisplayName("외부 API에서 제공하는 토큰을 반환한다.")
    @Test
    void getTokenByKakao() throws JsonProcessingException {
        final TokenResponse token = kakaoLoginService.getSocialToken(ACCESS_TOKEN);
        final String body = tokenResponse.getBody().readString(StandardCharsets.UTF_8);
        final TokenResponse expectedToken = objectMapper.readValue(body, TokenResponse.class);

        assertThat(token.getAccessToken()).isEqualTo(expectedToken.getAccessToken());
    }

    @DisplayName("외부 API에서 제공하는 유저정보를 받아온다.")
    @Test
    void getUserInfoByKakao() throws JsonProcessingException {
        final SocialInfo socialInfo = kakaoLoginService.getSocialInfo(ACCESS_TOKEN);
        final String body = userResponse.getBody().readString(StandardCharsets.UTF_8);
        final SocialInfo expectedInfo = objectMapper.readValue(body, SocialInfo.class);

        assertThat(socialInfo).isEqualToIgnoringNullFields(expectedInfo);
    }
}