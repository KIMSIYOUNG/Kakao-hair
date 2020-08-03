package com.example.kakaohair.member.web;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaohair.common.infra.kakao.LoginService;
import com.example.kakaohair.common.infra.kakao.TokenResponse;
import com.example.kakaohair.member.SocialInfo;
import com.example.kakaohair.member.application.MemberService;
import com.example.kakaohair.member.application.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<Void> loginBySocial(HttpServletResponse response) throws IOException {
        String codeUrl = loginService.getCodeUrl();
        response.sendRedirect(codeUrl);

        return ResponseEntity.ok().location(URI.create(codeUrl)).build();
    }

    @GetMapping("/oauth2/token")
    public ResponseEntity<TokenResponse> createToken(@RequestParam String code) {
        SocialInfo socialInfo = loginService.getSocialInfo(code);
        TokenResponse customToken = memberService.tokenFrom(socialInfo);

        return ResponseEntity.ok(customToken);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody MemberCreateRequest request) {
        Long id = memberService.create(request.toMember());

        return ResponseEntity.created(URI.create("/api/members/" + id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findByMemberId(@PathVariable Long id) {
        MemberResponse response = memberService.findByMemberId(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateByMemberId(@PathVariable Long id, @RequestBody @Valid MemberUpdateRequest request) {
        memberService.update(id, request);

        return ResponseEntity.ok()
            .header("Location", "/api/members/" + id)
            .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByMemberId(@PathVariable Long id) {
        memberService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
