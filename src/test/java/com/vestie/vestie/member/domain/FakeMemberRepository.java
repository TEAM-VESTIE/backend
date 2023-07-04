package com.vestie.vestie.member.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.test.util.ReflectionTestUtils;

public class FakeMemberRepository implements MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private Long id = 1L;

    @Override
    public Member save(Member member) {
        ReflectionTestUtils.setField(member, "id", id);
        return store.put(id++, member);
    }

    @Override
    public boolean existsByUsername(String username) {
        return store.values()
                .stream()
                .anyMatch(it -> it.username().equals(username));
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}