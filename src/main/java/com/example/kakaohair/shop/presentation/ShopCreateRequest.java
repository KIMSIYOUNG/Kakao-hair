package com.example.kakaohair.shop.presentation;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.example.kakaohair.shop.domain.BusinessHours;
import com.example.kakaohair.shop.domain.Photo;
import com.example.kakaohair.shop.domain.Shop;
import com.example.kakaohair.user.owner.Owner;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @ConstructorProperties({"name", "open", "close","description","phone"}))
public class ShopCreateRequest {
    @NotBlank
    private final String name;
    @NotBlank
    private final LocalDateTime open;
    @NotBlank
    private final LocalDateTime close;
    @NotBlank
    private final String description;
    @NotBlank
    private final String phone;

    public Shop toShop(Owner owner, List<Photo> images) {
        return Shop.builder()
            .owner(owner)
            .name(name)
            .description(description)
            .phone(phone)
            .businessHours(new BusinessHours(open, close))
            .images(images)
            .build();
    }
}
