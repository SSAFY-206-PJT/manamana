package com.webtoon.manamana.webtoon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WebtoonDetailDTO {

    private long id;
    private String name;
    private String imagePath;
    private String plot;
    private String grade;
    private String status;
    private String webtoonUrl;
    private String webtoonId;
    private String startDate;
    private int totalEpisode;
    private String colorHsl;
    private List<AuthorDTO> authors;
    private List<GenreDTO> genres;
    private List<DayDTO> days;
    private AdditionDTO additions;

    @Builder
    public WebtoonDetailDTO(long id, String name, String imagePath, String plot, String grade, String status, String webtoonUrl, String webtoonId, String startDate, int totalEpisode, String colorHsl, List<AuthorDTO> authors, List<GenreDTO> genres, List<DayDTO> days, AdditionDTO additions) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.plot = plot;
        this.grade = grade;
        this.status = status;
        this.webtoonUrl = webtoonUrl;
        this.webtoonId = webtoonId;
        this.startDate = startDate;
        this.totalEpisode = totalEpisode;
        this.colorHsl = colorHsl;
        this.authors = authors;
        this.genres = genres;
        this.days = days;
        this.additions = additions;
    }
}
