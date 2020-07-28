package com.example.kakaohair.member.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql("/truncate.sql")
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member firstMember;
    private Member secondMember;

    @BeforeEach
    void setUp() {
        firstMember = memberRepository.save(MemberFixture.memberWithOutId());
        secondMember = memberRepository.save(MemberFixture.memberWithOutId());
    }

    @Test
    void deleteById() {
        memberRepository.deleteById(firstMember.getId());
        final Member findMember = memberRepository.findById(firstMember.getId()).get();

        assertAll(
            () -> assertThat(firstMember).isEqualToIgnoringGivenFields(findMember, "memberState", "createdAt",
                "updatedAt"),
            () -> assertThat(secondMember.getMemberState()).isEqualTo(MemberState.ACTIVE),
            () -> assertThat(firstMember.getMemberState()).isEqualTo(MemberState.DELETED),
            () -> assertThat(findMember.getMemberState()).isEqualTo(MemberState.DELETED)
        );
    }

    @Test
    void deleteAll() {
        memberRepository.deleteAll();
        memberRepository.findAll()
            .forEach((member) -> assertThat(member.getMemberState()).isEqualTo(MemberState.DELETED));
    }
}