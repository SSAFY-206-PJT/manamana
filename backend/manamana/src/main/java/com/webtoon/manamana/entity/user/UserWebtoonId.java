package com.webtoon.manamana.entity.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class UserWebtoonId implements Serializable {

    @Column(name = "user_id")
    private long userId;
    @Column(name = "wetoon_id")
    private long webtoonId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWebtoonId that = (UserWebtoonId) o;

        if (userId != that.userId) return false;
        return webtoonId == that.webtoonId;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (webtoonId ^ (webtoonId >>> 32));
        return result;
    }

    @Builder
    public UserWebtoonId(long userId, long webtoonId) {
        this.userId = userId;
        this.webtoonId = webtoonId;
    }

    public static UserWebtoonId createUserWebtoonId(long userId, long webtoonId){

        return UserWebtoonId.builder()
                .userId(userId)
                .webtoonId(webtoonId)
                .build();
    }
}
