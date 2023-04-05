package com.manamana.crawling.entity.webtoon;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class WebtoonGenreId implements Serializable {

    @Column(name = "webtoon_id")
    private long webtoonId;

    @Column(name = "genre_id")
    private int genreId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebtoonGenreId that = (WebtoonGenreId) o;

        if (webtoonId != that.webtoonId) return false;
        return genreId == that.genreId;
    }


    @Override
    public int hashCode() {
        int result = (int) (webtoonId ^ (webtoonId >>> 32));
        result = 31 * result + genreId;
        return result;
    }

    @Builder
    public WebtoonGenreId(long webtoonId, int genreId) {
        this.webtoonId = webtoonId;
        this.genreId = genreId;
    }
}
