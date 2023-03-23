package com.webtoon.manamana.util.dto.response;

import com.webtoon.manamana.entity.webtoon.codetable.Genre;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreResponseDTO {
    private int id;
    private String name;

    @Builder
    public GenreResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GenreResponseDTO createDTO(Genre genre){

        return GenreResponseDTO.builder()
                .id(genre.getId())
                .name(genre.getName()).build();
    }
}
