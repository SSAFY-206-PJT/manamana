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

    /**
     * 웹툰 단건 데이터 분류 (장르, 웹툰, 요일, 작가)
     * @param webtoonDataDTO
     */
    SplitDTO splitWebtoonData(WebtoonDataDTO webtoonDataDTO);

    /**
     * 웹툰 저장
     * @param webtoonDTO
     */
    void saveWebtoon(WebtoonDTO webtoonDTO);

    /**
     * 장르 저장
     * @param genreDTO
     */
    void saveGenre(GenreDTO genreDTO);

    /**
     * 요일 저장
     * @param dayDTO
     */
    void saveDay(DayDTO dayDTO);

    /**
     * 작가 저장
     * @param authorDTO
     */
    void saveAuthor(AuthorDTO authorDTO);















    //TODO webtoonId, webtoonProvder둘다 확인하기
    void validateDuplicateWebtoon(WebtoonDataDTO webtoonDataDTO);


    /**
     * 웹툰 전체 조회
     */
    List<Webtoon> findWebtoon();

    /**
     * 웹툰 단건 조회(id)
     */
    Webtoon findOne(Long id);

    /**
     * 웹툰 단건 조회(webtoonId)
     */
    Webtoon findOneByWebtoonId(String webtoonId);

}
