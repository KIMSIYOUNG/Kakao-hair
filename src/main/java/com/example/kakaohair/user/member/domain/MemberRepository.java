package com.example.kakaohair.user.member.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.memberInfo.socialId = :socialId")
    Optional<Member> findBySocialId(String socialId);
}
