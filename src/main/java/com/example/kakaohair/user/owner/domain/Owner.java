package com.example.kakaohair.user.owner.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.example.kakaohair.user.owner.web.OwnerUpdateRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Entity
@Getter
public class Owner {
    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String license;

    public void update(final OwnerUpdateRequest request) {
        this.toBuilder()
            .name(request.getName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .license(request.getLicense())
            .build();
    }
}
