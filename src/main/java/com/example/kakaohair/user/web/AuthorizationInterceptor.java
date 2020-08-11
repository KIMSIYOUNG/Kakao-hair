package com.example.kakaohair.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.kakaohair.common.JwtGenerator;
import com.example.kakaohair.common.infra.kakao.TokenResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private final AuthorizationExtractor extractor;
    private final JwtGenerator jwtGenerator;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        if(!(handler instanceof HandlerMethod)) {
            return false;
        }
        if(isExcludePattern(request)) {
            return true;
        }

        TokenResponse tokenResponse = extractor.extract(request);
        final String socialId = jwtGenerator.getSubject(tokenResponse.getAccessToken());
        request.setAttribute("socialId", socialId);

        return true;
    }

    private boolean isExcludePattern(HttpServletRequest request) {
        return (HttpMethod.POST.matches(request.getMethod()) && request.getServletPath().equalsIgnoreCase("/api/members"));
    }
}
