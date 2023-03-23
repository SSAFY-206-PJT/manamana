package com.manamana.crawling.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GenreDTO {

    private Long webtoonId;

    private List<Integer> genreIdArr;

}
