package com.example.kakaohair.user.owner.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakaohair.common.exception.notfound.OwnerNotFoundException;
import com.example.kakaohair.user.owner.domain.Owner;
import com.example.kakaohair.user.owner.domain.OwnerRepository;
import com.example.kakaohair.user.owner.web.OwnerCreateRequest;
import com.example.kakaohair.user.owner.web.OwnerResponse;
import com.example.kakaohair.user.owner.web.OwnerUpdateRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public Long create(final OwnerCreateRequest request) {
        Owner owner = request.toOwner();
        Owner persistOwner = ownerRepository.save(owner);

        return persistOwner.getId();
    }

    public Owner retrieveById(Long id) {
        return findById(id);
    }

    public OwnerResponse retrieveOwnerById(Long id) {
        Owner owner = findById(id);

        return OwnerResponse.of(owner);
    }

    public void updateById(final Long id, final OwnerUpdateRequest request) {
        Owner findOwner = findById(id);
        findOwner.update(request);
    }

    private Owner findById(final Long id) {
        return ownerRepository.findById(id)
            .orElseThrow(() -> new OwnerNotFoundException(id));
    }

    public void deleteById(final Long id) {
        ownerRepository.deleteById(id);
    }
}
