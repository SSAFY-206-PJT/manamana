package com.webtoon.manamana.recommand.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssosiationApiRequestDTO {

    private long userId;
    private long webtoonId;
    private int score;

    @Builder
    public AssosiationApiRequestDTO(long userId, long webtoonId, int score) {
        this.userId = userId;
        this.webtoonId = webtoonId;
        this.score = score;
    }
}
