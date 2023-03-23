package com.manamana.crawling.dto;

<<<<<<< HEAD
import lombok.Builder;
=======
>>>>>>> 6b44a1aecf1d6faa1802c83e1ce09ba65b1953f1
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GenreDTO {

    private Long webtoonId;

    private List<Integer> genreIdArr;

<<<<<<< HEAD
    @Builder
    public GenreDTO(Long webtoonId, List<Integer> genreIdArr) {
        this.webtoonId = webtoonId;
        this.genreIdArr = genreIdArr;
    }
=======
>>>>>>> 6b44a1aecf1d6faa1802c83e1ce09ba65b1953f1
}
