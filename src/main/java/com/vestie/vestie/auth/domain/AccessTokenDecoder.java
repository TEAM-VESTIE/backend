package com.vestie.vestie.auth.domain;

public interface AccessTokenDecoder {

    Claims decode(String token);
}
