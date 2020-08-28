package com.example.kakaohair.user.member.domain.memberinfo;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.example.kakaohair.user.member.SocialInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class MemberInfo {
    @NotBlank
    private String name;

    private String socialId;

    @NotBlank
    private String email;

    private String profile;

    @NotBlank
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDateTime birthday;

    public static MemberInfo of(SocialInfo socialInfo) {
        return MemberInfo.builder()
            .name(socialInfo.getName())
            .socialId(socialInfo.getId())
            .email(socialInfo.getEmail())
            .profile(socialInfo.getProfile())
            .build();
    }

    public static MemberInfo of(String name, String socialId, String email, String profile,
        String phone, Gender gender, LocalDateTime birthday) {
        return MemberInfo.builder()
            .name(name)
            .socialId(socialId)
            .email(email)
            .profile(profile)
            .phone(phone)
            .gender(gender)
            .birthday(birthday)
            .build();
    }
}
