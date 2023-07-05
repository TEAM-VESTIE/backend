package com.vestie.vestie.auth;

import com.vestie.vestie.auth.domain.AccessToken;
import com.vestie.vestie.auth.domain.AccessTokenProvider;
import com.vestie.vestie.auth.domain.Claims;

public class FakeAccessTokenProvider implements AccessTokenProvider {

    @Override
    public AccessToken provide(Claims claims) {
        return new AccessToken("fake");
    }
}
