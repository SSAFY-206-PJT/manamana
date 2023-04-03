package com.webtoon.manamana.entity.webtoon;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "reports")
public class Report {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    public Report(long id, long userId, Comment comment) {
        this.id = id;
        this.userId = userId;
        this.comment = comment;
    }

    public static Report createReport(long userId, Comment comment){

        return Report.builder()
                .userId(userId)
                .comment(comment).build();
    }
}
