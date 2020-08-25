package com.example.kakaohair.shop.presentation;

import java.beans.ConstructorProperties;
import java.util.List;

import com.example.kakaohair.shop.domain.BusinessHours;
import com.example.kakaohair.shop.domain.Photo;
import com.example.kakaohair.shop.domain.Shop;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @ConstructorProperties({"id", "name", "description",
    "businessHours", "imageUrls", "phone"}))
@Builder
@Getter
public class ShopResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final BusinessHours businessHours;
    private final List<Photo> imageUrls;
    private final String phone;

    public static ShopResponse of(final Shop findShop) {
        return ShopResponse.builder()
            .id(findShop.getId())
            .name(findShop.getName())
            .description(findShop.getDescription())
            .businessHours(findShop.getBusinessHours())
            .imageUrls(findShop.getImages())
            .phone(findShop.getPhone())
            .build();
    }
}
