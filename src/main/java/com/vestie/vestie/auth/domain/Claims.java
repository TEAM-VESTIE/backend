package com.vestie.vestie.auth.domain;

import com.auth0.jwt.interfaces.Claim;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Claims {

    private final Map<String, String> claims;

    public Claims() {
        claims = new HashMap<>();
    }

    public Claims(Map<String, Claim> claims) {
        this.claims = new HashMap<>();
        for (Entry<String, Claim> entry : claims.entrySet()) {
            String value = String.valueOf(entry.getValue());
            String parsed = value.replaceAll("\"", "");
            this.claims.put(entry.getKey(), parsed);
        }
    }

    public static Claims fromId(Long id) {
        Claims claims = new Claims();
        claims.addClaims("id", String.valueOf(id));
        return claims;
    }

    public void addClaims(final String name, final String value) {
        claims.put(name, value);
    }

    public Long getId() {
        return Long.parseLong(claims.get("id"));
    }

    public Set<Entry<String, String>> entrySet() {
        return claims.entrySet();
    }
}
