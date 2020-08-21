package com.example.kakaohair.shop.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaohair.shop.application.ShopService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/shops")
@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;


}
