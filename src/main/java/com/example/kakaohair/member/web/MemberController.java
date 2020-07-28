package com.example.kakaohair.member.web;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaohair.member.application.MemberService;
import com.example.kakaohair.member.application.MemberUpdateRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findByMemberId(@PathVariable Long id) {
        MemberResponse response = memberService.findByMemberId(id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
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
