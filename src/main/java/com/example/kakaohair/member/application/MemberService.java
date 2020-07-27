package com.example.kakaohair.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakaohair.member.domain.Member;
import com.example.kakaohair.member.domain.MemberRepository;
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
}
