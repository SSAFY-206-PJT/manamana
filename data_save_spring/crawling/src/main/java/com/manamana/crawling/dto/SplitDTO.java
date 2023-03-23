package com.manamana.crawling.dto;

<<<<<<< HEAD
import lombok.Builder;
=======
>>>>>>> 6b44a1aecf1d6faa1802c83e1ce09ba65b1953f1
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SplitDTO {

    private GenreDTO genreDTO;

    private WebtoonDTO webtoonDTO;

    private DayDTO dayDTO;

    private AuthorDTO authorDTO;

<<<<<<< HEAD

    @Builder
    public SplitDTO(GenreDTO genreDTO, WebtoonDTO webtoonDTO, DayDTO dayDTO, AuthorDTO authorDTO) {
        this.genreDTO = genreDTO;
        this.webtoonDTO = webtoonDTO;
        this.dayDTO = dayDTO;
        this.authorDTO = authorDTO;
    }
=======
>>>>>>> 6b44a1aecf1d6faa1802c83e1ce09ba65b1953f1
}
