package com.vestie.vestie.auth.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties("jwt")
public class JwtProperties {

    private final String secretKey;
    private final int accessTokenExpirationPeriodDay;

    @ConstructorBinding
    public JwtProperties(String secretKey, int accessTokenExpirationPeriodDay) {
        this.secretKey = secretKey;
        this.accessTokenExpirationPeriodDay = accessTokenExpirationPeriodDay;
    }

    public String secretKey() {
        return secretKey;
    }

    public int accessTokenExpirationPeriodDay() {
        return accessTokenExpirationPeriodDay;
    }
}
