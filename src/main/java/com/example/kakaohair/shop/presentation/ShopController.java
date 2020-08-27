package com.example.kakaohair.shop.presentation;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.kakaohair.shop.application.ShopService;
import com.example.kakaohair.user.owner.domain.Owner;
import com.example.kakaohair.user.web.ShopOwner;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/shops")
@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> post(@ShopOwner Owner owner, List<MultipartFile> images, ShopCreateRequest request) {
        Long id = shopService.create(owner, images, request);

        return ResponseEntity.created(URI.create("/api/shops/" + id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopResponse> retrieveById(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.retrieveById(id));
    }
}
