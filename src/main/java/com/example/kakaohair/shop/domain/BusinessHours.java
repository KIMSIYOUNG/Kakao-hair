package com.example.kakaohair.shop.domain;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Embeddable
public class BusinessHours {
    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;
}
