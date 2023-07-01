package com.vestie.vestie.member.infrastructure.persistence;

import com.vestie.vestie.member.domain.Member;
import com.vestie.vestie.member.domain.MemberRepository;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends MemberRepository, JpaRepository<Member, Long> {

    @Nonnull
    @Override
    default Member getById(Long id) {
        return MemberRepository.super.getById(id);
    }
}
