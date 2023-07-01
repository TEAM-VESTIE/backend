package com.vestie.vestie.member.application.dto;

import com.vestie.vestie.member.domain.Member;

public record SignUpCommand(
        String username,
        String password,
        String name
) {
    public Member toDomain() {
        return Member.builder()
                .username(username)
                .password(password)
                .name(name)
                .build();
    }
}
