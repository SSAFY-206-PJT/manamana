package com.manamana.crawling.entity.webtoon;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "webtoon_days")
public class WebtoonDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code_id")
    private int codeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;

    @Builder
    public WebtoonDay(long id, int codeId, Webtoon webtoon) {
        this.id = id;
        this.codeId = codeId;
        this.webtoon = webtoon;
    }
}
