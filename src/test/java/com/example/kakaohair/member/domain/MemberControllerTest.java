package com.example.kakaohair.member.domain;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.kakaohair.common.ErrorCode;
import com.example.kakaohair.common.MemberNotFoundException;
import com.example.kakaohair.member.application.MemberService;
import com.example.kakaohair.member.web.MemberCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

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
}