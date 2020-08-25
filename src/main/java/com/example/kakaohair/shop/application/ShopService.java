package com.example.kakaohair.shop.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.kakaohair.common.exception.notfound.ShopNotFoundException;
import com.example.kakaohair.common.infra.upload.ImageUploadService;
import com.example.kakaohair.shop.domain.Photo;
import com.example.kakaohair.shop.domain.Shop;
import com.example.kakaohair.shop.domain.ShopRepository;
import com.example.kakaohair.shop.presentation.ShopCreateRequest;
import com.example.kakaohair.shop.presentation.ShopResponse;
import com.example.kakaohair.user.owner.Owner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopService {
    private final ImageUploadService uploadService;
    private final ShopRepository shopRepository;

    public Long create(Owner owner, List<MultipartFile> multipartFiles, ShopCreateRequest request) {
        List<Photo> images = uploadService.upload(multipartFiles);
        Shop shop = request.toShop(owner, images);
        Shop persistShop = shopRepository.save(shop);

        return persistShop.getId();
    }

    public ShopResponse retrieveById(final Long id) {
        Shop findShop = shopRepository.findById(id)
            .orElseThrow(() -> new ShopNotFoundException(id));

        return ShopResponse.of(findShop);
    }
}
