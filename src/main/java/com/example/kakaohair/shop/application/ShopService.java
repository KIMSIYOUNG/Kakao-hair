package com.example.kakaohair.shop.application;

import org.springframework.stereotype.Service;

import com.example.kakaohair.shop.domain.ShopRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopService {
    private final ShopRepository shopRepository;
}
