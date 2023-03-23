package com.manamana.crawling.dto;

import lombok.Builder;

import java.util.List;

public class AuthorDTO {

    private Long webtoonId;

    private List<String> nameArr;

    @Builder
    public AuthorDTO(Long webtoonId, List<String> nameArr) {
        this.webtoonId = webtoonId;
        this.nameArr = nameArr;
    }
}
