package com.example.kakaohair.member.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.kakaohair.member.domain.Member;
import com.example.kakaohair.member.domain.MemberFixture;
import com.example.kakaohair.member.domain.MemberRepository;
import com.example.kakaohair.member.web.MemberResponse;

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
        final Member expectedMember = MemberFixture.memberWithId();
        when(memberRepository.save(any(Member.class))).thenReturn(expectedMember);

        final Long createdId = memberService.create(MemberFixture.memberWithOutId());

        assertThat(createdId).isEqualTo(expectedMember.getId());
    }

    @DisplayName("회원의 ID로 회원을 조회한다.")
    @Test
    void findByMemberId() {
        final Member expectedMember = MemberFixture.memberWithId();
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(expectedMember));
        final MemberResponse findMember = memberService.findByMemberId(expectedMember.getId());

        assertThat(findMember).isEqualToIgnoringNullFields(expectedMember);
    }
}