package com.example.kakaohair.user.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakaohair.common.JwtGenerator;
import com.example.kakaohair.common.exception.MemberNotFoundException;
import com.example.kakaohair.common.infra.kakao.TokenResponse;
import com.example.kakaohair.user.member.SocialInfo;
import com.example.kakaohair.user.member.domain.Member;
import com.example.kakaohair.user.member.domain.MemberRepository;
import com.example.kakaohair.user.member.domain.hairinfo.HairInfo;
import com.example.kakaohair.user.member.domain.memberinfo.MemberInfo;
import com.example.kakaohair.user.member.web.MemberResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtGenerator jwtGenerator;

    public TokenResponse createMemberAndToken(SocialInfo socialInfo) {
        final Member member = memberRepository.findBySocialId(socialInfo.getId())
            .orElseGet(() -> createMember(Member.builder()
                .memberInfo(MemberInfo.of(socialInfo))
                .hairInfo(HairInfo.init())
                .build()));

        return jwtGenerator.createCustomToken(member.getMemberInfo().getSocialId());
    }

    private Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public TokenResponse create(Member member) {
        Member savedMember = memberRepository.save(member);
        return jwtGenerator.createCustomToken(savedMember.getMemberInfo().getSocialId());
    }

    @Transactional(readOnly = true)
    public MemberResponse findByMemberId(final Long id) {
        final Member member = find(id);

        return MemberResponse.from(member);
    }

    @Transactional(readOnly = true)
    public Member findBySocialId(String socialId) {
        return memberRepository.findBySocialId(socialId)
            .orElseThrow(() -> new MemberNotFoundException(socialId));
    }

    public void delete(final Long id) {
        memberRepository.deleteById(id);
    }

    private Member find(final Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException(memberId));
    }
}
