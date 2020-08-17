package com.example.kakaohair.user.member.domain.hairinfo;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;

@Getter
@Embeddable
public class HairInfo {

    @Enumerated(EnumType.STRING)
    private Scalp scalp = Scalp.UNCHECKED;

    @Enumerated(EnumType.STRING)
    private Thickness thickness = Thickness.UNCHECKED;

    @Enumerated(EnumType.STRING)
    private Curl curl = Curl.UNCHECKED;

    @Enumerated(EnumType.STRING)
    private HairCondition hairCondition = HairCondition.UNCHECKED;

    @Enumerated(EnumType.STRING)
    private Volume volume = Volume.UNCHECKED;

    @Enumerated(EnumType.STRING)
    private Amount amount = Amount.UNCHECKED;

    @Enumerated(EnumType.STRING)
    private GrayHair grayHair = GrayHair.UNCHECKED;

    public static HairInfo init() {
        return new HairInfo();
    }
}
