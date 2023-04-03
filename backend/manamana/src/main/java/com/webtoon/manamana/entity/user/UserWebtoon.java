package com.webtoon.manamana.entity.user;

import com.webtoon.manamana.config.entity.BaseTimeEntity;
import com.webtoon.manamana.entity.webtoon.Webtoon;
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

    @Builder
    public UserWebtoon(UserWebtoonId id, User user, Webtoon webtoon, int score, boolean isLiked, boolean isDeleted) {
        this.id = id;
        this.user = user;
        this.webtoon = webtoon;
        this.score = score;
        this.isLiked = isLiked;
        this.isDeleted = isDeleted;
    }

    public void removeUserWebtoon(){

        this.isLiked = false;

    }

    //생성 - 관심 등록
    public static UserWebtoon createLikeUserWebtoon(User user, Webtoon webtoon){
        return UserWebtoon.builder()
                .score(0)
                .id(UserWebtoonId.createUserWebtoonId(user.getId(),webtoon.getId()))
                .user(user)
                .webtoon(webtoon)
                .isLiked(true)
                .isDeleted(false)
                .build();

    }
    //생성 - 관심등록은 아니지만 평가했을때
    public static UserWebtoon createScoreUserWebtoon(User user, Webtoon webtoon, int score){
        return UserWebtoon.builder()
                .score(score)
                .id(UserWebtoonId.createUserWebtoonId(user.getId(),webtoon.getId()))
                .user(user)
                .webtoon(webtoon)
                .isLiked(false)
                .isDeleted(false)
                .build();
    }

    //관심등록
    public void updateLikedUserWebtoon(){
        this.isLiked = true;
    }

    //점수 업데이트
    public void updateScoreUserWebtoon(int score){
        this.score = score;
    }



}
