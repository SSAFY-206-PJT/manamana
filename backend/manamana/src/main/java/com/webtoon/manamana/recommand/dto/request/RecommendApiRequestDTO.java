package com.webtoon.manamana.recommand.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecommendApiRequestDTO {

    private long userId;
    private long webtoonId;
    private int score;

    @Builder
    public RecommendApiRequestDTO(long userId, long webtoonId, int score) {
        this.userId = userId;
        this.webtoonId = webtoonId;
        this.score = score;
    }
}
