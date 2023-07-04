package com.vestie.vestie.member.presentation.dto;

import com.vestie.vestie.member.application.dto.SignUpCommand;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String name
) {

    public SignUpCommand toCommand() {
        return new SignUpCommand(username, password, name);
    }
}
