package com.example.kakaohair.user.member.domain;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.example.kakaohair.BaseEntity;
import com.example.kakaohair.user.member.domain.hairinfo.HairInfo;
import com.example.kakaohair.user.member.domain.memberinfo.MemberInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {

    @Embedded
    private MemberInfo memberInfo;

    @Embedded
    private HairInfo hairInfo;

    @Builder(toBuilder = true)
    Member(Long id, MemberInfo memberInfo, HairInfo hairInfo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.memberInfo = memberInfo;
        this.hairInfo = hairInfo;
    }
}
