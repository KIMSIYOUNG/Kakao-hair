package com.example.kakaohair.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
    @Override
    void deleteById(Long id);

    @Override
    void delete(Member entity);

    @Override
    void deleteAll(Iterable<? extends Member> entities);

    @Override
    void deleteAll();
}
