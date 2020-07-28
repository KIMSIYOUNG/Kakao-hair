package com.example.kakaohair.member.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.example.kakaohair.BaseEntity;
import com.example.kakaohair.member.application.MemberUpdateRequest;
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

    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberState memberState;

    @Builder
    Member(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, MemberState memberState) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.memberState = memberState;
    }

    public void changeInfo(final MemberUpdateRequest request) {
        if(request.getName() != null) {
            this.name = request.getName();
        }
    }

    public void delete() {
        this.memberState = MemberState.DELETED;
    }
}
