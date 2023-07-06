package com.vestie.vestie.member.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import com.vestie.vestie.member.application.MemberService;
import com.vestie.vestie.member.application.dto.LoginResult;
import com.vestie.vestie.member.presentation.dto.LoginRequest;
import com.vestie.vestie.member.presentation.dto.LoginResponse;
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

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        LoginResult result = memberService.login(request.toCommand());
        LoginResponse response = new LoginResponse(result.accessToken().value(), result.name());
        return ResponseEntity.ok(response);
    }
}
