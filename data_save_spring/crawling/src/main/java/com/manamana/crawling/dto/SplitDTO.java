package com.manamana.crawling.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SplitDTO {

    private GenreDTO genreDTO;

    private WebtoonDTO webtoonDTO;

    private DayDTO dayDTO;

    private AuthorDTO authorDTO;


    @Builder
    public SplitDTO(GenreDTO genreDTO, WebtoonDTO webtoonDTO, DayDTO dayDTO, AuthorDTO authorDTO) {
        this.genreDTO = genreDTO;
        this.webtoonDTO = webtoonDTO;
        this.dayDTO = dayDTO;
        this.authorDTO = authorDTO;
    }
}
