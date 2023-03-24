package com.manamana.crawling.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WebtoonDataDTO {

    private String name;
    private String image;
    private String plot;
    private String grade;
    private String status;
    private String webtoon_url;
    private int webtoon_id;
    private String start_date;
    private int total_ep;
    private List<String> genre_arr;
    private List<String> day_arr;
    private List<String> authors_arr;
    private String colorHsl;


}
