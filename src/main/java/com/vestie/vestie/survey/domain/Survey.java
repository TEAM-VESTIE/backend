package com.vestie.vestie.survey.domain;


import static lombok.AccessLevel.PROTECTED;

import com.vestie.vestie.common.domain.BaseEntity;
import com.vestie.vestie.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Survey extends BaseEntity {

    @Column(length = 1000)
    private String formLink;

    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public String formLink() {
        return formLink;
    }

    public LocalDateTime endDate() {
        return endDate;
    }

    public Member member() {
        return member;
    }
}
