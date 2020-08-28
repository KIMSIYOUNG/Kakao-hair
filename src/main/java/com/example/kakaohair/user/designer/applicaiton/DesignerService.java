package com.example.kakaohair.user.designer.applicaiton;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakaohair.common.JwtGenerator;
import com.example.kakaohair.common.exception.notfound.MemberNotFoundException;
import com.example.kakaohair.common.infra.kakao.TokenResponse;
import com.example.kakaohair.user.designer.Designer;
import com.example.kakaohair.user.designer.domain.DesignerRepository;
import com.example.kakaohair.user.designer.web.DesignerCreateRequest;
import com.example.kakaohair.user.designer.web.DesignerInfoUpdateRequest;
import com.example.kakaohair.user.designer.web.DesignerResponse;
import com.example.kakaohair.user.member.SocialInfo;
import com.example.kakaohair.user.member.domain.memberinfo.MemberInfo;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class DesignerService {
    private final DesignerRepository designerRepository;
    private final JwtGenerator jwtGenerator;

    public TokenResponse createDesignerAndToken(final SocialInfo loginInfo) {
        Designer designer = designerRepository.findBySocialId(loginInfo.getId())
            .orElseGet(() -> createMember(Designer.builder()
                .memberInfo(MemberInfo.of(loginInfo))
                .build()));

        return jwtGenerator.createCustomToken(designer.getId());
    }

    private Designer createMember(final Designer designer) {
        return designerRepository.save(designer);
    }

    public Long create(DesignerCreateRequest request) {
        Designer savedDesigner = designerRepository.save(request.toDesigner());

        return savedDesigner.getId();
    }

    public DesignerResponse retrieveOwnerById(Long id) {
        Designer designer = designerRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException(id));

        return DesignerResponse.of(designer);
    }

    public void deleteById(Long id) {
        designerRepository.deleteById(id);
    }

    public void updateById(Long id, DesignerInfoUpdateRequest request) {
        Designer designer = designerRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException(id));

        designer.update(request);
    }
}
