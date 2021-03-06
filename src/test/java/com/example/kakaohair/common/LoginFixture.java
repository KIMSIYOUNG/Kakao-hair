package com.example.kakaohair.common;

import com.example.kakaohair.common.infra.kakao.KakaoTokenResponse;
import com.example.kakaohair.common.infra.kakao.KakaoUserResponse;

public class LoginFixture {
    public static final Long KAKAO_ID = 3L;
    public static final String EMAIL = "EMAIL";
    public static final String URL = "https://14floorguys.com";
    public static final String SERVER_URI = "http://localhost:8080";
    public static final String CLIENT_ID_VALUE = "1231234";
    public static final String CLIENT_SECRET_VALUE = "SECRET";
    public static final String RESPONSE_TYPE_VALUE = "RESPONSE";
    public static final String GRANT_TYPE_VALUE = "GRANT";
    public static final String AUTHORIZE_PATH = "/oauth/authorize";
    public static final String RESPONSE_TYPE = "response_type";
    public static final String CLIENT_ID = "client_id";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String REDIRECT_PATH = "/oauth/token";
    public static final String OAUTH_TOKEN_PATH = "/oauth/token";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String USER_INFO_PATH = "/v2/user/me";
    public static final String TOKEN_TYPE = "Bearer ";
    public static final String SCOPE = "scope ";
    public static final String NICKNAME = "nickname";
    public static final String BIRTHDAY = "0429";
    public static final int EXPIRE = 1;
    public static final boolean ADMIT = true;

    public static KakaoTokenResponse mockKakaoTokenResponse() {
        return KakaoTokenResponse.builder()
            .accessToken(ACCESS_TOKEN)
            .refreshToken(ACCESS_TOKEN)
            .refreshTokenExpiresIn(EXPIRE)
            .expiresIn(EXPIRE)
            .tokenType(TOKEN_TYPE)
            .scope(SCOPE)
            .build();
    }

    public static KakaoUserResponse mockKakaoUserResponse() {
        return KakaoUserResponse.builder()
            .id(KAKAO_ID)
            .nickname(NICKNAME)
            .profileImage(URL)
            .thumbnailImage(URL)
            .hasEmail(ADMIT)
            .isEmailValid(ADMIT)
            .isEmailVerified(ADMIT)
            .email(EMAIL)
            .emailNeedsAgreement(ADMIT)
            .hasBirthday(ADMIT)
            .birthdayNeedsAgreement(ADMIT)
            .birthday(BIRTHDAY)
            .genderNeedsAgreement(ADMIT)
            .build();
    }
}
