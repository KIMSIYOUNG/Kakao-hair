package com.example.kakaohair.user.owner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakaohair.common.exception.notfound.OwnerNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public Owner retrieveOwnerById(Long id) {
        return ownerRepository.findById(id)
            .orElseThrow(() -> new OwnerNotFoundException(id));
    }
}
