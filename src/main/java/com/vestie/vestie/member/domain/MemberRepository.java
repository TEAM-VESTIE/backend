package com.vestie.vestie.member.domain;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Nonnull
    default Member getById(Long id) {
        return findById(id)
                .orElseThrow();
    }
}
