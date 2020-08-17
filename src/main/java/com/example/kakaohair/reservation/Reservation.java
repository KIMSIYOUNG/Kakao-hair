package com.example.kakaohair.reservation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.kakaohair.BaseEntity;
import com.example.kakaohair.product.Product;
import com.example.kakaohair.user.designer.Designer;
import com.example.kakaohair.user.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Reservation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Designer designer;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;
}
