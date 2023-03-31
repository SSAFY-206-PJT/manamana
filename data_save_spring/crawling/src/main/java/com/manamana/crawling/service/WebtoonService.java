package com.manamana.crawling.service;

import com.manamana.crawling.dto.*;
import com.manamana.crawling.entity.webtoon.Webtoon;
import java.util.List;

public interface WebtoonService {

    /**
     * 웹툰 데이터 리스트 처리
     * @param webtoonDataArrayDTO
     */
    void webtoonsData(WebtoonDataArrayDTO webtoonDataArrayDTO);

//    /**
//     * 웹툰 저장
//     * @param webtoonDataDTO
//     */
//    Webtoon saveWebtoon(int provider, WebtoonDataDTO webtoonDataDTO);
//
//
//    /**
//     * 장르 저장
//     * @param webtoon
//     * @param webtoonDataDTO
//     */
//    void saveGenre(Webtoon webtoon, WebtoonDataDTO webtoonDataDTO);
//
//    /**
//     * 요일 저장
//     * @param webtoon
//     * @param webtoonDataDTO
//     */
//    void saveDay(Webtoon webtoon, WebtoonDataDTO webtoonDataDTO);
//
//    /**
//     * 작가 저장
//     * @param webtoon
//     * @param webtoonDataDTO
//     */
//    void saveAuthor(Webtoon webtoon, WebtoonDataDTO webtoonDataDTO);
//
//







}
