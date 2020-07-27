package com.example.kakaohair.member.domain;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaohair.member.application.MemberService;
import com.example.kakaohair.member.web.MemberCreateRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody MemberCreateRequest request) {
        Long id = memberService.create(request.toMember());

        return ResponseEntity.created(URI.create("/api/members/" + id)).build();
    }
}
