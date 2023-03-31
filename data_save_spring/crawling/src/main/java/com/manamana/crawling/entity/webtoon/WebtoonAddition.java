package com.manamana.crawling.entity.webtoon;

import com.manamana.crawling.config.entity.BaseTimeEntity;
import com.manamana.crawling.entity.webtoon.Webtoon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "webtoon_additions")
public class WebtoonAddition extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "view")
    private long view;
    @Column(name = "total_score")
    private long totalScore;
    @Column(name = "score_count")
    private long scoreCount;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;


    @Builder
    public WebtoonAddition(long id, long view, long totalScore, long scoreCount, Webtoon webtoon) {
        this.id = id;
        this.view = view;
        this.totalScore = totalScore;
        this.scoreCount = scoreCount;
        this.webtoon = webtoon;
    }

}
