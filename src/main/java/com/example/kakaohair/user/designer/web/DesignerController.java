package com.example.kakaohair.user.designer.web;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaohair.common.infra.kakao.LoginService;
import com.example.kakaohair.common.infra.kakao.TokenResponse;
import com.example.kakaohair.user.designer.applicaiton.DesignerService;
import com.example.kakaohair.user.member.SocialInfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/designers")
@RestController
public class DesignerController {
    private final DesignerService designerService;
    private final LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<Void> loginBySocial(HttpServletResponse response) throws IOException {
        String codeUrl = loginService.getCodeUrl();
        response.sendRedirect(codeUrl);

        return ResponseEntity.ok().location(URI.create(codeUrl)).build();
    }

    @GetMapping("/oauth2/token")
    public ResponseEntity<TokenResponse> createToken(@RequestParam String code) {
        final TokenResponse kakaoToken = loginService.getSocialToken(code);
        final SocialInfo loginInfo = loginService.getSocialInfo(kakaoToken);
        final TokenResponse customToken = designerService.createDesignerAndToken(loginInfo);

        return ResponseEntity.ok(customToken);
    }

    @PostMapping
    public ResponseEntity<Void> post(@Valid DesignerCreateRequest request) {
        Long id = designerService.create(request);

        return ResponseEntity.created(URI.create("/api/owners/" + id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesignerResponse> findById(@PathVariable Long id) {
        DesignerResponse response = designerService.retrieveOwnerById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid DesignerInfoUpdateRequest request) {
        designerService.updateById(id, request);

        return ResponseEntity.ok()
            .location(URI.create("/api/owners/" + id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        designerService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
