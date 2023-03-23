package com.manamana.crawling.entity.webtoon;

import com.manamana.crawling.entity.webtoon.codetable.Genre;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "webtoons_and_genres")
public class WebtoonGenre {

    @EmbeddedId
    private WebtoonGenreId id;

    @MapsId("genreId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @MapsId("webtoonId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;

    @Builder
    public WebtoonGenre(Genre genre, Webtoon webtoon) {
        this.genre = genre;
        this.webtoon = webtoon;
    }

}
