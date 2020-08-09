package com.example.kakaohair.user.member.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.example.kakaohair.BaseEntity;
import com.example.kakaohair.user.member.web.MemberUpdateRequest;
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

    private String socialId;

    @Builder(toBuilder = true)
    Member(Long id, String socialId, LocalDateTime createdAt, LocalDateTime updatedAt, String name) {
        super(id, createdAt, updatedAt);
        this.socialId = socialId;
        this.name = name;
    }

    public void changeInfo(MemberUpdateRequest request) {
        if(request.getName() != null) {
            this.name = request.getName();
        }
    }
}
