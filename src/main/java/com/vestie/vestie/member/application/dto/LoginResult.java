package com.vestie.vestie.member.application.dto;

import com.vestie.vestie.auth.domain.AccessToken;

public record LoginResult(
        AccessToken accessToken,
        String name
) {
}
