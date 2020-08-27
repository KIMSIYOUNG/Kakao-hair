package com.example.kakaohair.vo;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Cash {
    private BigDecimal cash;
}
