package com.webtoon.manamana.webtoon.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WebtoonFilterDTO {

    private List<Integer> statusId;
    private List<Integer> genreId;
    private List<Integer> gradeId;
    private List<Integer> dayId;

    private String keyword;
    private Integer sortType;
}
