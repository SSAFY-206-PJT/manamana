package com.manamana.crawling.dto;

import lombok.Builder;

import java.util.List;

public class DayDTO {

    private Long webtoonId;

    private List<Integer> codeId;

    @Builder
    public DayDTO(Long webtoonId, List<Integer> codeId) {
        this.webtoonId = webtoonId;
        this.codeId = codeId;
    }
}
