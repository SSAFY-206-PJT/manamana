package com.webtoon.manamana.webtoon.dto.response;

import com.webtoon.manamana.entity.webtoon.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {

    private int id;
    private String name;

    @Builder
    public AuthorDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AuthorDTO createDTO(Author author){

        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName()).build();
    }
}
