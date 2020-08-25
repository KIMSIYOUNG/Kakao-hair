package com.example.kakaohair.user.web;

import static com.example.kakaohair.user.member.domain.MemberFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.example.kakaohair.common.exception.notfound.MemberNotFoundException;
import com.example.kakaohair.common.exception.invalid.TokenInvalidException;
import com.example.kakaohair.user.member.application.MemberService;
import com.example.kakaohair.user.member.domain.Member;

@ExtendWith(MockitoExtension.class)
class LoginMemberArgumentResolverTest {
    public static final int LOGIN_MEMBER_INDEX = 0;
    public static final int NOT_LOGIN_MEMBER_INDEX = 1;

    @Mock
    private MemberService memberService;

    private LoginMemberArgumentResolver argumentResolver;

    private MethodParameter supportedMethodParameter;

    private MethodParameter unSupportedMethodParameter;

    private ServletWebRequest servletRequest;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        argumentResolver = new LoginMemberArgumentResolver(memberService);
        supportedMethodParameter = new MethodParameter(
            TestController.class.getMethod("loginMemberTest", Member.class, Member.class), LOGIN_MEMBER_INDEX);
        unSupportedMethodParameter = new MethodParameter(
            TestController.class.getMethod("loginMemberTest", Member.class, Member.class), NOT_LOGIN_MEMBER_INDEX);
        servletRequest = new ServletWebRequest(new MockHttpServletRequest());
    }

    public static class TestController {
        public void loginMemberTest(@LoginMember Member member, Member notLoginMember) {
        }
    }

    @DisplayName("@LoginMember에 해당하는 어노테이션을 검증하는지 테스트한다.")
    @Test
    void supportLoginMember() {
        final boolean isSupport = argumentResolver.supportsParameter(supportedMethodParameter);
        assertThat(isSupport).isTrue();
    }

    @DisplayName("어노테이션이 없는 경우 False를 정상 반환한다.")
    @Test
    void unSupportLoginMember() {
        final boolean isSupport = argumentResolver.supportsParameter(unSupportedMethodParameter);
        assertThat(isSupport).isFalse();
    }

    @DisplayName("Member를 정상적으로 반환한다.")
    @Test
    void resolveArgument() {
        final Member expected = memberWithId();
        when(memberService.findBySocialId(any())).thenReturn(expected);
        servletRequest.setAttribute("socialId", SOCIAL_ID, RequestAttributes.SCOPE_REQUEST);

        final Member member = argumentResolver.resolveArgument(supportedMethodParameter, null, servletRequest, null);

        assertThat(member).isEqualToIgnoringNullFields(expected);
    }

    @DisplayName("토큰이 잘못된 경우 토큰 예외를 반환한다.")
    @Test
    void invalidTokenException() {
        servletRequest.setAttribute("socialId", null, RequestAttributes.SCOPE_REQUEST);

        assertThatThrownBy(
            () -> argumentResolver.resolveArgument(supportedMethodParameter, null, servletRequest, null)
        ).isInstanceOf(TokenInvalidException.class);
    }

    @DisplayName("토큰은 해체했으나, 회원이 존재하지 않는 경우 예외를 반환한다.")
    @Test
    void memberNotFoundException() {
        when(memberService.findBySocialId(any())).thenThrow(MemberNotFoundException.class);
        servletRequest.setAttribute("socialId", SOCIAL_ID, RequestAttributes.SCOPE_REQUEST);

        assertThatThrownBy(
            () -> argumentResolver.resolveArgument(supportedMethodParameter, null, servletRequest, null)
        ).isInstanceOf(MemberNotFoundException.class);
    }
}