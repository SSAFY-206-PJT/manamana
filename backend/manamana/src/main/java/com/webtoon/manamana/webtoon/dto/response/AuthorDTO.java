package com.webtoon.manamana.webtoon.dto.response;

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
}
