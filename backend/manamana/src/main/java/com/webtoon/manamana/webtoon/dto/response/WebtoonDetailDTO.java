package com.webtoon.manamana.webtoon.dto.response;

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
}
