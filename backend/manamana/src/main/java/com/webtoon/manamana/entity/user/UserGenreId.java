package com.webtoon.manamana.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class UserGenreId implements Serializable {

    @Column(name = "user_id")
    private long userId;

    @Column(name = "genre_id")
    private int genreId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGenreId that = (UserGenreId) o;

        if (this.userId != that.userId) return false;
        return this.genreId == that.genreId;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + genreId;
        return result;
    }
}
