package com.vestie.vestie.auth.domain;

public interface AccessTokenProvider {

    AccessToken provide(Claims claims);
}
