package com.webtoon.manamana.webtoon.dto.response;

import com.webtoon.manamana.entity.webtoon.codetable.Genre;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO {

    private int id;
    private String name;

    @Builder
    public GenreDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GenreDTO createDTO(Genre genre){

        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName()).build();
    }
}
