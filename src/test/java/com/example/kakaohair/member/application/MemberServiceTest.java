package com.example.kakaohair.member.application;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.kakaohair.member.domain.Member;
import com.example.kakaohair.member.domain.MemberFixture;
import com.example.kakaohair.member.domain.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        this.memberService = new MemberService(memberRepository);
    }

    @DisplayName("회원이 정상적으로 생성된다.")
    @Test
    void create() {
        // when(memberRepository.save(any(Member.class))).thenReturn(MemberFixture.memberWithId());
    }
}