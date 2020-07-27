package com.example.kakaohair.member.domain;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.example.kakaohair.common.exception.ErrorCode;
import com.example.kakaohair.common.exception.MemberNotFoundException;
import com.example.kakaohair.member.application.MemberService;
import com.example.kakaohair.member.web.MemberController;
import com.example.kakaohair.member.web.MemberCreateRequest;
import com.example.kakaohair.member.web.MemberResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MemberController.class)
class MemberControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .alwaysDo(print())
            .build();
    }

    @DisplayName("회원을 생성하는 요청을 정상적으로 처리한다.")
    @Test
    void create() throws Exception {
        final Member member = MemberFixture.memberWithId();
        when(memberService.create(any(Member.class))).thenReturn(member.getId());

        final MemberCreateRequest request = MemberFixture.createDto();

        mockMvc.perform(post("/api/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request))
        )
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/members/1"));
    }

    @DisplayName("회원을 생성 요청의 body 가 잘못된 경우 Invalid 된다.")
    @Test
    void createBadRequest() throws Exception {
        MemberCreateRequest request = MemberFixture.createWrongDto();
        when(memberService.create(any(Member.class))).thenReturn(MemberFixture.memberWithId().getId());
        mockMvc.perform(post("/api/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request))
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("code").value(ErrorCode.INVALID_REQUEST.getCode()))
            .andExpect(jsonPath("errors").exists());
    }

    @DisplayName("회원의 ID로 회원을 조회한다.")
    @Test
    void findMemberById() throws Exception {
        final MemberResponse expectedResponse = MemberResponse.from(MemberFixture.memberWithId());
        when(memberService.findByMemberId(anyLong())).thenReturn(expectedResponse);

        mockMvc.perform(get("/api/members/1000")
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").value(expectedResponse.getId()))
            .andExpect(jsonPath("name").value(expectedResponse.getName()));
    }

    @DisplayName("존재하지 않는 회원을 조회하는 경우 예외를 반환한다.")
    @Test
    void findNotExistMember() throws Exception {
        when(memberService.findByMemberId(anyLong())).thenThrow(new MemberNotFoundException(1000L));

        mockMvc.perform(get("/api/members/1000")
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("code").value(ErrorCode.MEMBER_NOT_FOUND.getCode()));
    }
}