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

    @Builder
    Member(final Long id, final LocalDateTime createdAt, final LocalDateTime updatedAt, String name) {
        super(id, createdAt, updatedAt);
        this.name = name;
    }
}
