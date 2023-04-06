package com.manamana.crawling.entity.user;

import com.manamana.crawling.config.entity.BaseTimeEntity;
import com.manamana.crawling.entity.webtoon.Webtoon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "users_and_webtoons")
public class UserWebtoon extends BaseTimeEntity {

    @EmbeddedId
    private UserWebtoonId id;

    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @MapsId("webtoonId")
    @JoinColumn(name = "webtoon_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Webtoon webtoon;

    @Column(name = "score")
    private int score;

    @Column(name = "is_liked")
    private boolean isLiked;

    @Column(name = "is_deleted")
    private boolean isDeleted;


    public void removeUserWebtoon(){

        this.isLiked = false;

    }

    @Builder
    public UserWebtoon(UserWebtoonId id, User user, Webtoon webtoon, int score, boolean isLiked, boolean isDeleted) {
        this.id = id;
        this.user = user;
        this.webtoon = webtoon;
        this.score = score;
        this.isLiked = isLiked;
        this.isDeleted = isDeleted;
    }
}
