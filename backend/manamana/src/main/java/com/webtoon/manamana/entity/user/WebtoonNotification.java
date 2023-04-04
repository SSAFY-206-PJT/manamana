package com.webtoon.manamana.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Table(name = "webtoon_notifications")
public class WebtoonNotification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "webtoon_id")
    private long webtoonId;

    @Column(name = "episode")
    private int episode;

    @Column(name = "is_checked")
    private boolean isChecked;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
