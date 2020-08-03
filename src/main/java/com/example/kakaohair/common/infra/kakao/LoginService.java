package com.example.kakaohair.common.infra.kakao;

import com.example.kakaohair.member.SocialInfo;

public interface LoginService {

    String getCodeUrl();

    TokenResponse getSocialToken(String code);

    SocialInfo getSocialInfo(TokenResponse socialToken);
}
