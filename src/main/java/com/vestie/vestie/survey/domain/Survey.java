package com.vestie.vestie.survey.domain;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import com.vestie.vestie.common.domain.BaseEntity;
import com.vestie.vestie.member.domain.Member;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Survey extends BaseEntity {

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder.Default
    @OneToMany(fetch = LAZY, cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "survey_id", nullable = false, updatable = false)
    private List<Question> questions = new ArrayList<>();

    public String title() {
        return title;
    }

    public LocalDateTime endDate() {
        return endDate;
    }

    public Member member() {
        return member;
    }

    public String description() {
        return description;
    }

    public List<Question> questions() {
        return new ArrayList<>(questions);
    }
}
