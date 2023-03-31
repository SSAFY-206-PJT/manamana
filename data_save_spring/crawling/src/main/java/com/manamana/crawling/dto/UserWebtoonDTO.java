package com.manamana.crawling.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseWebtoonDTO {
    private long userId;

    private long webtoonId;

    private int score;

    @Builder
    public ResponseWebtoonDTO(long userId, long webtoonId, int score) {
        this.userId = userId;
        this.webtoonId = webtoonId;
        this.score = score;
    }

    public static ResponseWebtoonDTO createDTO(long userId, long webtoonId, int score) {
        return ResponseWebtoonDTO.builder()
                .userId(userId)
                .webtoonId(webtoonId)
                .score(score)
                .build();
    }
}
