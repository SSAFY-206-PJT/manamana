package com.webtoon.manamana.entity.webtoon;


import com.webtoon.manamana.config.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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


    //총 점수, 사람 업데이트
    public void updateTotalScore(int score){
        this.totalScore +=score;
    }

    public void updateScoreCount(){
        this.scoreCount++;
    }

}
