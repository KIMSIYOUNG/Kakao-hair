package com.example.kakaohair.user.web;

import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.kakaohair.common.exception.ErrorCode;
import com.example.kakaohair.common.exception.TokenInvalidException;
import com.example.kakaohair.user.member.application.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private final MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String socialId = (String)webRequest.getAttribute("socialId", RequestAttributes.SCOPE_REQUEST);

        if (Objects.isNull(socialId)) {
            throw new TokenInvalidException("유효하지 않은 회원입니다.", ErrorCode.TOKEN_INVALID);
        }

        return memberService.findBySocialId(socialId);
    }
}
