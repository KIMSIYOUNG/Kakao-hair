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
import com.example.kakaohair.common.exception.invalid.TokenInvalidException;
import com.example.kakaohair.user.owner.Owner;

@Component
public class OwnerArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ShopOwner.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        Owner owner = (Owner)webRequest.getAttribute("owner", RequestAttributes.SCOPE_REQUEST);

        if (Objects.isNull(owner)) {
            throw new TokenInvalidException("유효하지 않은 회원입니다.", ErrorCode.TOKEN_INVALID);
        }

        return owner;
    }
}
