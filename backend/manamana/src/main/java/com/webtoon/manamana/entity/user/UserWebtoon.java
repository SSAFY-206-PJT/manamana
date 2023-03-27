package com.webtoon.manamana.entity.user;

import com.webtoon.manamana.config.entity.BaseTimeEntity;
import com.webtoon.manamana.entity.webtoon.Webtoon;
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


}
