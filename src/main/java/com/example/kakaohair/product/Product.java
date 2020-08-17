package com.example.kakaohair.product;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.example.kakaohair.BaseEntity;
import com.example.kakaohair.shop.Shop;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;
}
