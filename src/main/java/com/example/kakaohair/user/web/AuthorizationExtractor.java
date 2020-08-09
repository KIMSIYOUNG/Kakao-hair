package com.example.kakaohair.user.web;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.example.kakaohair.common.exception.ErrorCode;
import com.example.kakaohair.common.exception.TokenInvalidException;
import com.example.kakaohair.common.infra.kakao.TokenResponse;

@Component
public class AuthorizationExtractor {
    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_TYPE = "bearer";
    private static final int TOKEN_SIZE = 2;
    private static final int TOKEN_TYPE_INDEX = 0;
    private static final int TOKEN_INDEX = 1;

    public TokenResponse extract(final HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);
        if (Objects.isNull(header)) {
            throw new TokenInvalidException("유효하지 않은 토큰입니다.", ErrorCode.TOKEN_INVALID);
        }

        String[] splitToken = header.split(" ");
        if (splitToken.length != TOKEN_SIZE || !(splitToken[TOKEN_TYPE_INDEX].equalsIgnoreCase(TOKEN_TYPE))) {
            throw new TokenInvalidException("유효하지 않은 토큰입니다.", ErrorCode.TOKEN_INVALID);
        }

        return TokenResponse.of(splitToken[TOKEN_INDEX]);
    }
}
