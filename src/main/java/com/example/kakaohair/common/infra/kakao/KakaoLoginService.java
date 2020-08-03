package com.example.kakaohair.common.infra.kakao;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.example.kakaohair.member.SocialInfo;

@Service
public class KakaoLoginService implements LoginService {
    private static final String AUTHORIZE_PATH = "/oauth/authorize";
    private static final String RESPONSE_TYPE = "response_type";
    private static final String CLIENT_ID = "client_id";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String REDIRECT_PATH = "/oauth/token";
    private static final String OAUTH_TOKEN_PATH = "/oauth/token";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CODE = "code";
    private static final String USER_INFO_PATH = "/v2/user/me";
    private static final String AUTHORIZATION = "Authorization";
    private static final String AUTHORIZATION_TYPE = "Bearer ";

    private final String authorizeUri;
    private final String apiUri;
    private final String serverUri;
    private final String clientIdValue;
    private final String clientSecretValue;
    private final String responseTypeValue;
    private final String grantType;

    public KakaoLoginService(
        @Value("${kakao.authorizeUri}") String authorizeUri,
        @Value("${kakao.apiUri}") String apiUri,
        @Value("${server.uri}") String serverUri,
        @Value("${kakao.clientId}") String clientIdValue,
        @Value("${kakao.clientSecret}") String clientSecretValue,
        @Value("${kakao.responseType}") String responseTypeValue,
        @Value("${kakao.grantType}") String grantType) {
        this.authorizeUri = authorizeUri;
        this.apiUri = apiUri;
        this.serverUri = serverUri;
        this.clientIdValue = clientIdValue;
        this.clientSecretValue = clientSecretValue;
        this.responseTypeValue = responseTypeValue;
        this.grantType = grantType;
    }

    @Override
    public String getCodeUrl() {
        return new DefaultUriBuilderFactory().builder()
            .path(authorizeUri + AUTHORIZE_PATH)
            .queryParam(RESPONSE_TYPE, responseTypeValue)
            .queryParam(CLIENT_ID, clientIdValue)
            .queryParam(REDIRECT_URI, serverUri + REDIRECT_PATH)
            .build().toString();
    }

    @Override
    public TokenResponse getSocialToken(String code) {
        final WebClient webClient = WebClient.builder()
            .baseUrl(authorizeUri)
            .build();

        final KakaoTokenResponse kakaoTokenResponse = webClient.post()
            .uri(uriBuilder -> uriBuilder
                .path(OAUTH_TOKEN_PATH)
                .queryParam(GRANT_TYPE, grantType)
                .queryParam(CLIENT_ID, clientIdValue)
                .queryParam(CLIENT_SECRET, clientSecretValue)
                .queryParam(CODE, code)
                .queryParam(REDIRECT_URI, serverUri + REDIRECT_PATH)
                .build())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(KakaoTokenResponse.class)
            .block();

        return TokenResponse.of(Objects.requireNonNull(kakaoTokenResponse, "kakao(token) bad request"));
    }

    @Override
    public SocialInfo getSocialInfo(final TokenResponse kakaoToken) {
        WebClient webClient = WebClient.builder()
            .baseUrl(apiUri)
            .build();

        final KakaoUserResponse kakaoUserResponse = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(USER_INFO_PATH)
                .build())
            .header(AUTHORIZATION, AUTHORIZATION_TYPE + kakaoToken.getAccessToken())
            .retrieve()
            .bodyToMono(KakaoUserResponse.class)
            .block();

        return SocialInfo.from(Objects.requireNonNull(kakaoUserResponse, "kakao(userInfo) bad request"));
    }
}
