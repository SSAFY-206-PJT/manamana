package com.manamana.crawling.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GenreDTO {

    private Long webtoonId;

    private List<Integer> genreIdArr;

    @Builder
    public GenreDTO(Long webtoonId, List<Integer> genreIdArr) {
        this.webtoonId = webtoonId;
        this.genreIdArr = genreIdArr;
    }
}
