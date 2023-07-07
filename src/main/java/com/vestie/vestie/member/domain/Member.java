package com.vestie.vestie.member.domain;

import static com.vestie.vestie.member.exception.MemberExceptionType.AUTHENTICATION_FAIL;
import static lombok.AccessLevel.PROTECTED;

import com.vestie.vestie.common.domain.BaseEntity;
import com.vestie.vestie.member.exception.MemberException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

    @Column(length = 100, unique = true)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String name;

    public void signUp(MemberValidator validator) {
        validator.validateDuplicateUsername(username);
    }

    public void login(String password) {
        if (!this.password.equals(password)) {
            throw new MemberException(AUTHENTICATION_FAIL);
        }
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String name() {
        return name;
    }
}
