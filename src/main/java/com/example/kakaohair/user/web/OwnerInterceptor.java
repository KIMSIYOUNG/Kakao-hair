package com.example.kakaohair.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.kakaohair.common.JwtGenerator;
import com.example.kakaohair.common.exception.invalid.RequestInvalidException;
import com.example.kakaohair.common.infra.kakao.TokenResponse;
import com.example.kakaohair.user.owner.Owner;
import com.example.kakaohair.user.owner.OwnerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OwnerInterceptor extends HandlerInterceptorAdapter {
    private final AuthorizationExtractor extractor;
    private final JwtGenerator jwtGenerator;
    private final OwnerService ownerService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        if(!(handler instanceof HandlerMethod)) {
            return false;
        }

        if(isExcludePattern(request)) {
            return true;
        }

        TokenResponse tokenResponse = extractor.extract(request);
        final String ownerId = jwtGenerator.getSubject(tokenResponse.getAccessToken());

        try {
            Owner owner = ownerService.retrieveOwnerById(Long.parseLong(ownerId));
            request.setAttribute("owner", owner);
        } catch (NumberFormatException e) {
            throw new RequestInvalidException(ownerId);
        }

        return true;
    }

    private boolean isExcludePattern(HttpServletRequest request) {
        return (request.getServletPath().contains("/api/shops") && HttpMethod.GET.name().equals(request.getMethod()));
    }
}
