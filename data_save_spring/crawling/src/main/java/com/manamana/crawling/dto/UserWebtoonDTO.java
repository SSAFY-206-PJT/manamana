package com.manamana.crawling.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserWebtoonDTO {
    private long userId;

    private long webtoonId;

    private int score;

    @Builder
    public UserWebtoonDTO(long userId, long webtoonId, int score) {
        this.userId = userId;
        this.webtoonId = webtoonId;
        this.score = score;
    }

    public static UserWebtoonDTO createDTO(long userId, long webtoonId, int score) {
        return UserWebtoonDTO.builder()
                .userId(userId)
                .webtoonId(webtoonId)
                .score(score)
                .build();
    }
}
