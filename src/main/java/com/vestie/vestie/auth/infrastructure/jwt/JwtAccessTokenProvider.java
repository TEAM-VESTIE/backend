package com.vestie.vestie.auth.infrastructure.jwt;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.vestie.vestie.auth.domain.AccessToken;
import com.vestie.vestie.auth.domain.AccessTokenProvider;
import com.vestie.vestie.auth.domain.Claims;
import com.vestie.vestie.auth.domain.JwtProperties;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessTokenProvider implements AccessTokenProvider {

    private final Algorithm algorithm;
    private final long accessTokenExpirationPeriodMills;

    public JwtAccessTokenProvider(JwtProperties properties) {
        this.algorithm = Algorithm.HMAC512(properties.secretKey());
        this.accessTokenExpirationPeriodMills = MILLISECONDS.convert(
                properties.accessTokenExpirationPeriodDay(),
                DAYS);
    }

    @Override
    public AccessToken provide(Claims claims) {
        Builder builder = JWT.create();
        setUpExpiration(builder);
        setUpClaims(builder, claims);
        return new AccessToken(builder.sign(algorithm));
    }

    private void setUpExpiration(Builder builder) {
        Date date = new Date(accessTokenExpirationPeriodMills + System.currentTimeMillis());
        builder.withExpiresAt(date);
    }

    private void setUpClaims(Builder builder, Claims claims) {
        claims.entrySet().forEach(entry ->
                builder.withClaim(entry.getKey(), entry.getValue()));
    }
}
