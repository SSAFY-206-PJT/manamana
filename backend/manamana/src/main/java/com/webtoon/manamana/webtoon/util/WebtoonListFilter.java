package com.webtoon.manamana.webtoon.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


/**
 * - keyword - 검색어(웹툰/작가명)
 * - statusId - 연재 여부
 * - genreId - 장르
 * - gradeId - 연령 등급
 * - dayId - 요일
 * - sortType - 정렬 조건
 *     - view - 조회순
 *     - score - 별점 순
 *     - comment - 댓글 순
 */
@Getter
@Setter
public class WebtoonListFilter {

    private String keyword;
    private Integer statusId;
    private Integer genreId;
    private Integer gradeId;
    private Integer dayId;
    private Integer sortType;

    @Builder
    public WebtoonListFilter(String keyword, Integer statusId, Integer genreId, Integer gradeId, Integer dayId, Integer sortType) {
        this.keyword = keyword;
        this.statusId = statusId;
        this.genreId = genreId;
        this.gradeId = gradeId;
        this.dayId = dayId;
        this.sortType = sortType;
    }
}
