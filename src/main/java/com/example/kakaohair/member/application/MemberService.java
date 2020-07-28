package com.example.kakaohair.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakaohair.common.exception.MemberNotFoundException;
import com.example.kakaohair.member.domain.Member;
import com.example.kakaohair.member.domain.MemberRepository;
import com.example.kakaohair.member.web.MemberResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Long create(Member member) {
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    @Transactional(readOnly = true)
    public MemberResponse findByMemberId(final Long id) {
        final Member member = find(id);

        return MemberResponse.from(member);
    }

    public void update(final Long id, MemberUpdateRequest request) {
        final Member member = find(id);
        member.changeInfo(request);
    }

    public void delete(final Long id) {
        memberRepository.deleteById(id);
    }

    private Member find(final Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException(memberId));
    }
}
