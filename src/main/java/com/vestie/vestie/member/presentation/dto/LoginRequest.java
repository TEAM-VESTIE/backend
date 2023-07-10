package com.vestie.vestie.member.presentation.dto;

import com.vestie.vestie.member.application.dto.LoginCommand;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {
    public LoginCommand toCommand() {
        return new LoginCommand(username, password);
    }
}
