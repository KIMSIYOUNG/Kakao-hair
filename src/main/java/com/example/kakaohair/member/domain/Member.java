package com.example.kakaohair.member.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.example.kakaohair.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {

    @NotBlank
    private String name;

    public Member(String name) {
        this.name = name;
    }


    Member(final Long id, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
    }
}
