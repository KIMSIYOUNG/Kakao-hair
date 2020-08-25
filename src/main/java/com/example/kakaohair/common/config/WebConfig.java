package com.example.kakaohair.common.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.kakaohair.user.web.MemberInterceptor;
import com.example.kakaohair.user.web.LoginMemberArgumentResolver;
import com.example.kakaohair.user.web.OwnerArgumentResolver;
import com.example.kakaohair.user.web.OwnerInterceptor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final MemberInterceptor memberInterceptor;
    private final OwnerInterceptor ownerInterceptor;
    private final LoginMemberArgumentResolver loginMemberArgumentResolver;
    private final OwnerArgumentResolver ownerArgumentResolver;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(memberInterceptor);
        registry.addInterceptor(ownerInterceptor)
            .addPathPatterns("/api/shops");
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginMemberArgumentResolver);
        resolvers.add(ownerArgumentResolver);
    }
}
