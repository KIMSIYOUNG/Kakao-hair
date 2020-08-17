package com.example.kakaohair.shop;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.example.kakaohair.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Shop extends BaseEntity {
    @NotBlank
    private String name;
}
