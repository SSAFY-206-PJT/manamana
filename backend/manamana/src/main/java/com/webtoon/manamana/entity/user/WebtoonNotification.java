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
@Table(name = "webtoon_notifications")
public class WebtoonNotification extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "episode")
    private int episode;

    @Column(name = "is_checked")
    private boolean isChecked;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "webtoon_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Webtoon webtoon;


    @Builder
    public WebtoonNotification(long id, int episode, boolean isChecked, User user, Webtoon webtoon) {
        this.id = id;
        this.episode = episode;
        this.isChecked = isChecked;
        this.user = user;
        this.webtoon = webtoon;
    }

    public static WebtoonNotification createWebtoonNotification(int episode,User user, Webtoon webtoon){

        return WebtoonNotification.builder()
                .episode(episode)
                .isChecked(false)
                .user(user)
                .webtoon(webtoon).build();

    }

    public void deleteNotification(){
        this.isChecked = true;
    }


}
