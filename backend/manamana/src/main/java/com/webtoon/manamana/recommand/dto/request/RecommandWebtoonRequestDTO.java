package com.webtoon.manamana.recommand.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecommandWebtoonRequestDTO {

    private long id;
    private String name;
    private String grade;
    private String status;
//    private List<AuthorDTO> authors;
//    private List<GenreDTO> genres;
//    private List<DayDTO> days;
    private String authors;
    private String genres;
    private String days;

    @Builder
    public RecommandWebtoonRequestDTO(long id, String name, String grade, String status, String authors, String genres, String days) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.status = status;
        this.authors = authors;
        this.genres = genres;
        this.days = days;
    }
}
