package com.webtoon.manamana.webtoon.dto.response.common;

import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.webtoon.dto.response.AdditionDTO;
import com.webtoon.manamana.webtoon.dto.response.AuthorDTO;
import com.webtoon.manamana.webtoon.dto.response.DayDTO;
import com.webtoon.manamana.webtoon.dto.response.GenreDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static WebtoonDetailDTO createDTO(Webtoon webtoon,List<GenreDTO> genreDTO, Map<Integer,String> statusMap,Map<Integer,String> gradeMap){

        List<AuthorDTO> authorDTOS = webtoon.getAuthors().stream()
                .map(AuthorDTO::createDTO)
                .collect(Collectors.toList());

        List<DayDTO> dayDTOS = webtoon.getWebtoonDays().stream()
                .map(DayDTO::createDTO)
                .collect(Collectors.toList());



        return WebtoonDetailDTO.builder()
                .id(webtoon.getId())
                .name(webtoon.getName())
                .imagePath(webtoon.getImagePath())
                .plot(webtoon.getPlot())
                .grade(gradeMap.get(webtoon.getGradeId()))
                .status(statusMap.get(webtoon.getStatusId()))
                .webtoonUrl(webtoon.getProviderId().getName() + webtoon.getWebtoonUrl())
                .webtoonId(webtoon.getWebtoonId())
                .startDate(webtoon.getStartDate().toString())
                .totalEpisode(webtoon.getTotalEp())
                .colorHsl(webtoon.getColorHsl())
                .authors(authorDTOS)
                .genres(genreDTO)
                .days(dayDTOS)
                .additions(AdditionDTO.createDTO(webtoon.getWebtoonAddition())).build();
    }
}
