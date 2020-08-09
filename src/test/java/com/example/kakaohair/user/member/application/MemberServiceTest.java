package com.example.kakaohair.user.member.application;

import static com.example.kakaohair.user.member.domain.MemberFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.kakaohair.common.JwtGenerator;
import com.example.kakaohair.common.infra.kakao.TokenResponse;
import com.example.kakaohair.user.member.domain.Member;
import com.example.kakaohair.user.member.domain.MemberFixture;
import com.example.kakaohair.user.member.domain.MemberRepository;
import com.example.kakaohair.user.member.web.MemberResponse;
import com.example.kakaohair.user.member.web.MemberUpdateRequest;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private JwtGenerator jwtGenerator;

    @BeforeEach
    void setUp() {
        this.memberService = new MemberService(memberRepository, jwtGenerator);
    }

    @DisplayName("회원이 정상적으로 생성된다.")
    @Test
    void create() {
        final Member expectedMember = MemberFixture.memberWithId();
        when(memberRepository.save(any(Member.class))).thenReturn(expectedMember);
        when(jwtGenerator.createCustomToken(anyString())).thenReturn(TokenResponse.of(anyString()));

        final TokenResponse tokenResponse = memberService.create(memberWithOutId());

        assertThat(tokenResponse.getAccessToken()).isNotNull();
    }

    @DisplayName("회원의 ID로 회원을 조회한다.")
    @Test
    void findByMemberId() {
        final Member expectedMember = MemberFixture.memberWithId();
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(expectedMember));
        final MemberResponse findMember = memberService.findByMemberId(expectedMember.getId());

        assertThat(findMember).isEqualToIgnoringNullFields(expectedMember);
    }

    @DisplayName("회원의 이름을 변경한 경우 정상 반영된다.")
    @Test
    void updateName() {
        final Member originMember = MemberFixture.memberWithId();
        final MemberUpdateRequest updateDto = MemberFixture.updateDto();
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(MemberFixture.updatedMember()));

        memberService.update(originMember, updateDto);
        final MemberResponse response = memberService.findByMemberId(originMember.getId());

        assertThat(updateDto.getName()).isEqualTo(response.getName());
    }

    @DisplayName("회원을 삭제하면 repository를 실행시킨다.")
    @Test
    void delete() {
        memberService.delete(anyLong());
        verify(memberRepository).deleteById(anyLong());
    }

    @DisplayName("소셜 ID가 존재하는 경우, 회원을 정상 조회한다.")
    @Test
    void findBySocialId() {
        given(memberRepository.findBySocialId(anyString())).willReturn(Optional.of(MemberFixture.socialMember()));
        final TokenResponse expectedToken = TokenResponse.of(MemberFixture.socialMember().getSocialId());
        given(jwtGenerator.createCustomToken(anyString())).willReturn(expectedToken);

        final TokenResponse actual = memberService.createMemberAndToken(MemberFixture.socialInfo());
        assertThat(actual.getAccessToken()).isEqualTo(expectedToken.getAccessToken());
    }

    @DisplayName("소셜 아이디가 없는 경우 회원을 생성한다.")
    @Test
    void createdBySocialId() {
        given(memberRepository.findBySocialId(anyString())).willReturn(Optional.empty());
        given(memberRepository.save(any(Member.class))).willReturn(MemberFixture.socialMember());
        final TokenResponse expectedToken = TokenResponse.of(SOCIAL_ID);
        given(jwtGenerator.createCustomToken(anyString())).willReturn(expectedToken);

        final TokenResponse actual = memberService.createMemberAndToken(MemberFixture.socialInfo());
        assertThat(actual.getAccessToken()).isEqualTo(expectedToken.getAccessToken());
    }
}