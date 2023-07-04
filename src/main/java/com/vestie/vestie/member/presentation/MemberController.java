package com.vestie.vestie.member.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import com.vestie.vestie.member.application.MemberService;
import com.vestie.vestie.member.presentation.dto.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    ResponseEntity<Void> signUp(
            @Valid @RequestBody SignUpRequest request
    ) {
        memberService.signUp(request.toCommand());
        return ResponseEntity.status(CREATED)
                .build();
    }
}
