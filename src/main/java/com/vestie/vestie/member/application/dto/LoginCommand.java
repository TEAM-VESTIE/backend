package com.vestie.vestie.member.application.dto;

public record LoginCommand(
        String username,
        String password
) {
}
