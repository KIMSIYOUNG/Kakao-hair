package com.example.kakaohair.shop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.example.kakaohair.BaseEntity;
import com.example.kakaohair.user.owner.domain.Owner;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Shop extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

    @ElementCollection
    @CollectionTable(name = "SHOP_IMAGE", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Photo> images = new ArrayList<>();

    @NotBlank
    private String name;

    @Embedded @Valid
    private BusinessHours businessHours;

    private String description;

    private String phone;
}
