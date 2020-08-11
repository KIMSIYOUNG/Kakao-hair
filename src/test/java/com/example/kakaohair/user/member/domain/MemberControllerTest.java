package com.example.kakaohair.user.member.domain;

import static com.example.kakaohair.user.member.domain.MemberFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.kakaohair.common.exception.ErrorCode;
import com.example.kakaohair.common.infra.kakao.LoginService;
import com.example.kakaohair.common.infra.kakao.TokenResponse;
import com.example.kakaohair.docs.MemberDocumentation;
import com.example.kakaohair.user.member.SocialInfo;
import com.example.kakaohair.user.member.application.MemberService;
import com.example.kakaohair.user.member.web.MemberCreateRequest;
import com.example.kakaohair.user.member.web.MemberResponse;
import com.example.kakaohair.user.member.web.MemberUpdateRequest;
import com.example.kakaohair.user.web.AuthorizationInterceptor;
import com.example.kakaohair.user.web.LoginMemberArgumentResolver;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest {
    private static final String REDIRECT_URL = "https:/kauth.kakao.com/oauth/authorize?response_type=code&client_id=AAA&redirect_uri=BBB";

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @MockBean
    private AuthorizationInterceptor authorizationInterceptor;

    @MockBean
    private LoginMemberArgumentResolver resolver;

    @MockBean
    private LoginService loginService;

    @BeforeEach
    void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(documentationConfiguration(restDocumentation))
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .alwaysDo(print())
            .build();
    }

    @DisplayName("회원을 생성하는 요청을 정상적으로 처리한다.")
    @Test
    void create() throws Exception {
        when(memberService.create(any(Member.class))).thenReturn(TokenResponse.of("TEST"));
        when(authorizationInterceptor.preHandle(any(), any(), any())).thenReturn(true);

        final MemberCreateRequest request = MemberFixture.createDto();

        mockMvc.perform(post("/api/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request))
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("accessToken").isNotEmpty())
            .andDo(MemberDocumentation.createMember());
    }

    @DisplayName("로그인을 요청하면 소셜로그인 페이지로 리다이랙트 한다.")
    @Test
    void login() throws Exception {
        when(loginService.getCodeUrl()).thenReturn(REDIRECT_URL);
        when(authorizationInterceptor.preHandle(any(), any(), any())).thenReturn(true);

        final MvcResult mvcResult = mockMvc.perform(get("/api/members/login"))
            .andExpect(status().is3xxRedirection())
            .andDo(MemberDocumentation.redirectToLogin())
            .andReturn();
        final String location = mvcResult.getResponse().getHeader("Location");

        assertAll(
            () -> assertThat(location.contains("response_type")).isTrue(),
            () -> assertThat(location.contains("client_id")).isTrue(),
            () -> assertThat(location.contains("redirect_uri")).isTrue()
        );
    }

    @DisplayName("토큰을 정상적으로 반환한다.")
    @Test
    void createToken() throws Exception {
        when(authorizationInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        final TokenResponse tokenExample = TokenResponse.of("TEST");
        when(loginService.getSocialToken(anyString())).thenReturn(tokenExample);
        final SocialInfo socialInfoExample = MemberFixture.socialInfo();
        when(loginService.getSocialInfo(tokenExample)).thenReturn(socialInfoExample);
        when(memberService.createMemberAndToken(socialInfoExample)).thenReturn(TokenResponse.of("TEST"));

        mockMvc.perform(get("/api/members/oauth2/token")
            .param("code", "TEST")
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("accessToken").value("TEST"))
            .andDo(MemberDocumentation.createSocialToken());
    }

    @DisplayName("회원을 생성 요청의 body 가 잘못된 경우 Invalid 된다.")
    @Test
    void createBadRequest() throws Exception {
        MemberCreateRequest request = MemberFixture.createWrongDto();
        when(memberService.create(any(Member.class))).thenReturn(TokenResponse.of("TEST"));
        when(authorizationInterceptor.preHandle(any(), any(), any())).thenReturn(true);

        mockMvc.perform(post("/api/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request))
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("code").value(ErrorCode.INVALID_REQUEST.getCode()))
            .andExpect(jsonPath("errors").exists())
            .andDo(MemberDocumentation.createMemberFail());
    }

    @DisplayName("회원이 자신의 정보를 조회한다.")
    @Test
    void findMemberById() throws Exception {
        final Member expectedMember = memberWithId();
        when(authorizationInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        when(resolver.supportsParameter(any())).thenReturn(true);
        when(resolver.resolveArgument(any(MethodParameter.class), any(ModelAndViewContainer.class),
            any(NativeWebRequest.class), any(WebDataBinderFactory.class))).thenReturn(expectedMember);

        mockMvc.perform(get("/api/members")
            .header(HttpHeaders.AUTHORIZATION, "TEST TOKEN")
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").value(expectedMember.getId()))
            .andExpect(jsonPath("name").value(expectedMember.getName()))
            .andDo(MemberDocumentation.findMyInfo());
    }

    @DisplayName("회원의 이름을 수정하는 요청을 정상 처리한다.")
    @Test
    void updateName() throws Exception {
        when(authorizationInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        final MemberUpdateRequest request = MemberFixture.updateDto();

        mockMvc.perform(put("/api/members")
            .header(HttpHeaders.AUTHORIZATION, "TEST_TOKEN")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request))
        )
            .andExpect(status().isOk())
            .andExpect(header().exists("Location"))
            .andDo(MemberDocumentation.updateName());
    }

    @DisplayName("회원 탈퇴 한다.")
    @Test
    void deleteById() throws Exception {
        when(memberService.findByMemberId(any())).thenReturn(MemberResponse.from(memberWithId()));
        when(authorizationInterceptor.preHandle(any(), any(), any())).thenReturn(true);

        mockMvc.perform(delete("/api/members")
            .header(HttpHeaders.AUTHORIZATION, "TEST_TOKEN")
        )
            .andExpect(status().isNoContent())
            .andDo(MemberDocumentation.deleteById());
    }
}