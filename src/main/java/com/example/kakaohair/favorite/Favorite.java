package com.example.kakaohair.favorite;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.example.kakaohair.BaseEntity;
import com.example.kakaohair.user.designer.Designer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Favorite extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Designer designer;
}
