package com.example.kakaohair.user.web;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import com.example.kakaohair.common.JwtGenerator;
import com.example.kakaohair.common.infra.kakao.TokenResponse;

@ExtendWith(MockitoExtension.class)
class MemberInterceptorTest {
    public static final String TEST_TOKEN = "TEST_TOKEN";
    public static final String TEST_SOCIAL_ID = "TEST_SOCIAL_ID";

    private MemberInterceptor interceptor;
    @Mock
    private AuthorizationExtractor extractor;
    @Mock
    private JwtGenerator jwtGenerator;

    @BeforeEach
    void setUp() {
        interceptor = new MemberInterceptor(extractor, jwtGenerator);
    }

    public static class TestController {
        public void testMethod() {

        }
    }

    @DisplayName("정상적으로 동작하는 preHandle")
    @Test
    void preHandle() throws NoSuchMethodException {
        when(extractor.extract(any())).thenReturn(TokenResponse.of(TEST_TOKEN));
        when(jwtGenerator.getSubject(TEST_TOKEN)).thenReturn(TEST_SOCIAL_ID);

        final MockHttpServletRequest request = new MockHttpServletRequest();
        boolean result = interceptor.preHandle(request, new MockHttpServletResponse(),
            new HandlerMethod(TestController.class, TestController.class.getMethod("testMethod")));
        assertThat(result).isTrue();
        assertThat(request.getAttribute("socialId")).isEqualTo(TEST_SOCIAL_ID);
    }

    @DisplayName("Handler Method가 아닌 경우 false를 리턴한다.")
    @Test
    void preHandleWithNoHandlerMethod() {
        assertThat(interceptor.preHandle(
            new MockHttpServletRequest(),
            new MockHttpServletResponse(),
            new Object()))
            .isFalse();
    }

    @DisplayName("exclude 되어야하는 것은 false를 리턴한다.")
    @Test
    void preHandleWithExclude() throws NoSuchMethodException {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.POST.name());
        request.setServletPath("/api/members");

        assertThat(
            interceptor.preHandle(request,
                new MockHttpServletResponse(),
                new HandlerMethod(TestController.class, TestController.class.getMethod("testMethod"))))
            .isTrue();
    }
}