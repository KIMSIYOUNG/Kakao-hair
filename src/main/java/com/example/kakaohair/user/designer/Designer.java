package com.example.kakaohair.user.designer;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.example.kakaohair.BaseEntity;
import com.example.kakaohair.shop.domain.Shop;
import com.example.kakaohair.user.designer.web.DesignerInfoUpdateRequest;
import com.example.kakaohair.user.member.domain.hairinfo.HairInfo;
import com.example.kakaohair.user.member.domain.memberinfo.MemberInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Getter
@Entity
public class Designer extends BaseEntity {

    @Embedded
    private MemberInfo memberInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @Builder(toBuilder = true)
    Designer(Long id, MemberInfo memberInfo, Shop shop, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.memberInfo = memberInfo;
        this.shop = shop;
    }

    public void update(DesignerInfoUpdateRequest request) {
        this.toBuilder()
            .memberInfo(request.getMemberInfo())
            .build();
    }
}
