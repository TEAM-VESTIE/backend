package com.vestie.vestie.member.domain;

import static lombok.AccessLevel.PROTECTED;

import com.vestie.vestie.common.domain.BaseEntity;
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

    @Column(length = 100)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String name;

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
