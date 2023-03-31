package com.webtoon.manamana.entity.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "prefer_genres")
public class PreferGenre {


    @Column(name = "id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "genre_id")
    private int genreId;

    @Column(name = "is_canceled")
    private boolean isCanceled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}


