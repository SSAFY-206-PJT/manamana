package com.manamana.crawling.dto.request;

import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.entity.webtoon.WebtoonProvider;
import com.manamana.crawling.repository.WebtoonProviderRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CreateWebtoonDTO {

    @Autowired
    WebtoonProviderRepository webtoonProviderRepository;


    private String name;

    private String imagePath;

    private String plot;

    private int gradeId;

    private int serialId;

    private String webtoonUrl;

    private String webtoonId;

    private LocalDate startDate;

    private int totalEp;

    private String colorHsl;

    private int providerId;

//    @Builder
//    public CreateWebtoonDTO(String name, String imagePath, String plot, int gradeId, int serialId, String webtoonUrl, String webtoonId, LocalDate startDate, int totalEp, String colorHsl, WebtoonProvider providerId) {
//        this.name = name;
//        this.imagePath = imagePath;
//        this.plot = plot;
//        this.gradeId = gradeId;
//        this.serialId = serialId;
//        this.webtoonUrl = webtoonUrl;
//        this.webtoonId = webtoonId;
//        this.startDate = startDate;
//        this.totalEp = totalEp;
//        this.colorHsl = colorHsl;
//        this.providerId = providerId;
//    }


    public Webtoon createWebtoon() {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return Webtoon.builder()
                .name(this.name)
                .imagePath(this.imagePath)
                .plot(this.plot)
                .gradeId(this.gradeId)
                .serialId(this.serialId)
                .webtoonUrl(this.webtoonUrl)
                .webtoonId(this.webtoonId)
                .startDate(this.startDate)
                .totalEp(this.totalEp)
                .colorHsl(this.colorHsl)
                .isDeleted(false)
                .providerId(webtoonProviderRepository.findOne(1))
                .build();
    }

}
