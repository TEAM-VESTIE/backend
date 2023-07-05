package com.vestie.vestie.common.config;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import com.auth0.jwt.algorithms.Algorithm;
import com.vestie.vestie.auth.domain.AccessTokenProvider;
import com.vestie.vestie.auth.domain.JwtProperties;
import com.vestie.vestie.auth.infrastructure.jwt.JwtAccessTokenProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

    @Bean
    public Algorithm algorithm(JwtProperties properties) {
        return Algorithm.HMAC512(properties.secretKey());
    }

    @Bean
    public AccessTokenProvider accessTokenProvider(Algorithm algorithm, JwtProperties properties) {
        return new JwtAccessTokenProvider(
                algorithm,
                MILLISECONDS.convert(
                        properties.accessTokenExpirationPeriodDay(),
                        DAYS)
        );
    }
}
