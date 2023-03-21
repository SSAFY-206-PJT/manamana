package com.webtoon.manamana.webtoon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WebtoonListDTO {

    private long id;
    private String name;
    private String status;
    private String imagePath;
    private List<AuthorDTO> authors;

    @Builder
    public WebtoonListDTO(long id, String name, String status, String imagePath, List<AuthorDTO> authors) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.imagePath = imagePath;
        this.authors = authors;
    }
}
