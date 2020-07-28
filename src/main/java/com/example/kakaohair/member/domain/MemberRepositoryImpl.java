package com.example.kakaohair.member.domain;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
public class MemberRepositoryImpl implements MemberCustomRepository {
    private final EntityManager manager;

    public void deleteById(Long id) {
        final Member findMember = manager.find(Member.class, id);
        findMember.delete();
    }

    public void delete(Member entity) {
        entity.delete();
    }

    public void deleteAll(Iterable<? extends Member> entities) {
        entities.forEach(Member::delete);
    }

    public void deleteAll() {
        manager.createQuery("select m from Member m", Member.class)
            .getResultList()
            .forEach(Member::delete);
    }
}
