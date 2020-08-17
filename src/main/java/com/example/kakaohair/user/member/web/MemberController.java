package com.example.kakaohair.user.member.web;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaohair.common.infra.kakao.LoginService;
import com.example.kakaohair.common.infra.kakao.TokenResponse;
import com.example.kakaohair.user.member.SocialInfo;
import com.example.kakaohair.user.member.application.MemberService;
import com.example.kakaohair.user.member.domain.Member;
import com.example.kakaohair.user.web.LoginMember;
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
        final TokenResponse kakaoToken = loginService.getSocialToken(code);
        final SocialInfo loginInfo = loginService.getSocialInfo(kakaoToken);
        final TokenResponse customToken = memberService.createMemberAndToken(loginInfo);

        return ResponseEntity.ok(customToken);
    }

    @PostMapping
    public ResponseEntity<TokenResponse> create(@Valid @RequestBody MemberCreateRequest request) {
        final TokenResponse token = memberService.create(request.toMember());

        return ResponseEntity.ok(token);
    }

    @GetMapping
    public ResponseEntity<MemberResponse> findByMemberId(@LoginMember Member member) {
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByMemberId(@LoginMember Member member) {
        memberService.delete(member.getId());

        return ResponseEntity.noContent().build();
    }
}
