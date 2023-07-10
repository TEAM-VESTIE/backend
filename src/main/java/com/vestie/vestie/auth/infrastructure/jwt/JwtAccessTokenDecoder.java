package com.vestie.vestie.auth.infrastructure.jwt;

import static com.vestie.vestie.auth.execption.AuthExceptionType.INVALID_ACCESS_TOKEN;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vestie.vestie.auth.domain.AccessTokenDecoder;
import com.vestie.vestie.auth.domain.Claims;
import com.vestie.vestie.auth.execption.AuthException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAccessTokenDecoder implements AccessTokenDecoder {

    private final Algorithm algorithm;

    @Override
    public Claims decode(String token) {
        DecodedJWT verify = decodeJWT(token);
        return new Claims(verify.getClaims());
    }

    private DecodedJWT decodeJWT(String token) {
        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (Exception e) {
            throw new AuthException(INVALID_ACCESS_TOKEN);
        }
    }
}
