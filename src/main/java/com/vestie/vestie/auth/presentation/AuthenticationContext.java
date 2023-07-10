package com.vestie.vestie.auth.presentation;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuthenticationContext {

    private Long principal;

    public Long principal() {
        return principal;
    }

    public void setPrincipal(Long principal) {
        this.principal = principal;
    }
}
