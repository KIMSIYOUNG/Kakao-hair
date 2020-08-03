package com.example.kakaohair.common;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.kakaohair.common.exception.ErrorCode;
import com.example.kakaohair.common.exception.TokenInvalidException;
import com.example.kakaohair.common.infra.kakao.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
    private final String secret;
    private final Long expireTime;

    public JwtGenerator(@Value("${jwt.secret.key}") String secret, @Value("${jwt.expire.time}") Long expireTime) {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
        this.expireTime = expireTime;
    }

    public TokenResponse createCustomToken(String socialId) {
        final Claims claims = Jwts.claims().setSubject(socialId);

        Date now = new Date();
        Date validateTime = new Date(now.getTime() + expireTime);

        return TokenResponse.of(Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secret)
            .setClaims(claims)
            .setExpiration(validateTime)
            .setIssuedAt(now)
            .compact());
    }

    public String getSubject(String token) {
        return validateToken(token).getBody().getSubject();
    }

    private Jws<Claims> validateToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new TokenInvalidException("토큰이 만료되었습니다. 다시 로그인 해주세요.", ErrorCode.TOKEN_EXPIRED);
        }
    }
}
