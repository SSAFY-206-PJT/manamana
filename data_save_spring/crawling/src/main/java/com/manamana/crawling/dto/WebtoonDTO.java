package com.manamana.crawling.dto;

import com.manamana.crawling.entity.webtoon.WebtoonProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class WebtoonDTO {

    private String name;
    private String imagePath;
    private String plot;
    private int gradeId;
    private int statusId;
    private String webtoonUrl;
    private String webtoonId;
    private LocalDate startDate;
    private int totalEp;
    private Boolean isDeleted;
    private String colorHsl;
    private WebtoonProvider providerId;

    @Builder
    public WebtoonDTO(String name, String imagePath, String plot, int gradeId, int statusId, String webtoonUrl, String webtoonId, LocalDate startDate, int totalEp, String colorHsl, boolean isDeleted, WebtoonProvider providerId) {
        this.name = name;
        this.imagePath = imagePath;
        this.plot = plot;
        this.gradeId = gradeId;
        this.statusId = statusId;
        this.webtoonUrl = webtoonUrl;
        this.webtoonId = webtoonId;
        this.startDate = startDate;
        this.totalEp = totalEp;
        this.colorHsl = colorHsl;
        this.isDeleted = isDeleted;
        this.providerId = providerId;
    }
}
