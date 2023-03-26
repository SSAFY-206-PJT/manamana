package com.webtoon.manamana.webtoon.dto.response;

import com.webtoon.manamana.entity.webtoon.WebtoonAddition;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bytecode.Addition;

@Getter
@Setter
public class AdditionDTO {

    private long view;
    private long scoreCount;
    private String scoreAverage;

    @Builder
    public AdditionDTO(long view, long scoreCount, String scoreAverage) {
        this.view = view;
        this.scoreCount = scoreCount;
        this.scoreAverage = scoreAverage;
    }

    public static AdditionDTO createDTO(WebtoonAddition webtoonAddition){

        return AdditionDTO.builder()
                .view(webtoonAddition.getView())
                .scoreCount(webtoonAddition.getScoreCount())
                .scoreAverage(String.format("%.2f", webtoonAddition.getTotalScore()/webtoonAddition.getScoreCount()))
                .build();
    }
}
