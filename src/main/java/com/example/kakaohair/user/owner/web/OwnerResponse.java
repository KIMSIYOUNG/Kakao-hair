package com.example.kakaohair.user.owner.web;

import java.beans.ConstructorProperties;

import com.example.kakaohair.user.owner.domain.Owner;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @ConstructorProperties({"id","name","phone","email","license"}))
@Builder
@Getter
public class OwnerResponse {
    private final Long id;
    private final String name;
    private final String phone;
    private final String email;
    private final String license;

    public static OwnerResponse of (Owner owner) {
        return OwnerResponse.builder()
            .id(owner.getId())
            .email(owner.getEmail())
            .name(owner.getName())
            .license(owner.getLicense())
            .phone(owner.getPhone())
            .build();
    }
}
