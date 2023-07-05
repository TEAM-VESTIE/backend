package com.vestie.vestie.auth.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Claims {

    private final Map<String, String> claims;

    public Claims() {
        this.claims = new HashMap<>();
    }

    public static Claims fromId(Long id) {
        Claims claims = new Claims();
        claims.addClaims("id", String.valueOf(id));
        return claims;
    }

    public void addClaims(final String name, final String value) {
        claims.put(name, value);
    }

    public Set<Entry<String, String>> entrySet() {
        return claims.entrySet();
    }
}
