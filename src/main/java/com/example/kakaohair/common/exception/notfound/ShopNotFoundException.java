package com.example.kakaohair.common.exception.notfound;

import com.example.kakaohair.common.exception.ErrorCode;

public class ShopNotFoundException extends NotFoundException {

    public ShopNotFoundException(Long id) {
        super(String.format("Shop(shop id = %d not exist)", id), ErrorCode.SHOP_NOT_FOUND);
    }
}
