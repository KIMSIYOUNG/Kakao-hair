package com.example.kakaohair.product;

import javax.persistence.Embeddable;

import com.example.kakaohair.user.member.domain.memberinfo.Gender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Description {
    private Gender gender;
    private CutOption cutOption;
    private String description;
}
