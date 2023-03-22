package com.webtoon.manamana.webtoon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionDTO {

    private long id;
    private long view;
    private long scoreCount;
    private long scoreAverage;

    @Builder
    public AdditionDTO(long id, long view, long scoreCount, long scoreAverage) {
        this.id = id;
        this.view = view;
        this.scoreCount = scoreCount;
        this.scoreAverage = scoreAverage;
    }
}
