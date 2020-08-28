package com.example.kakaohair.user.owner.web;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaohair.user.owner.application.OwnerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/owners")
@RestController
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Void> post(@Valid OwnerCreateRequest request) {
        Long id = ownerService.create(request);

        return ResponseEntity.created(URI.create("/api/owners/" + id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> findById(@PathVariable Long id) {
        OwnerResponse ownerResponse = ownerService.retrieveOwnerById(id);

        return ResponseEntity.ok(ownerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid OwnerUpdateRequest request) {
        ownerService.updateById(id, request);

        return ResponseEntity.ok()
            .location(URI.create("/api/owners/" + id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        ownerService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
