package com.webtoon.manamana.entity.user;

import com.webtoon.manamana.config.entity.BaseTimeEntity;
import com.webtoon.manamana.entity.webtoon.codetable.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "users_and_genres")
public class UserGenre extends BaseTimeEntity {

    @EmbeddedId
    private UserGenreId userGenreId;

    @Column(name = "weight")
    private int weight;

    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @MapsId("genreId")
    @JoinColumn(name = "genre_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Genre genre;


}
