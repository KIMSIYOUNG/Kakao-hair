package com.example.kakaohair.product;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.example.kakaohair.BaseEntity;
import com.example.kakaohair.shop.domain.Shop;
import com.example.kakaohair.vo.Cash;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product extends BaseEntity {

    private String name;

    @Embedded
    private Description description;

    @Embedded
    private Cash price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;
}
