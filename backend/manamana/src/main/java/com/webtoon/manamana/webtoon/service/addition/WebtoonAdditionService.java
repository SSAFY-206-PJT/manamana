package com.webtoon.manamana.webtoon.service.addition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.webtoon.manamana.webtoon.dto.response.addition.ScoreResponseDTO;
import com.webtoon.manamana.webtoon.dto.response.addition.WordCloudResponseDTO;

import java.util.List;

public interface WebtoonAdditionService {

    /*작품 댓글 신고기능*/
    void commentReport(long userId,long webtoonId, long commentId);

    /*작품 관심 등록*/
    void createLikeWebtoon(long userId,long webtoonId);

    /*댓글 워드 클라우드*/
    List<WordCloudResponseDTO> getWordCloudData(long webtoonId) throws JsonProcessingException;

    /*개인이 평가한 평점*/
    ScoreResponseDTO getWebtoonUserScore(long userId, long webtoonId);

    /*작품 평점 생성 및 수정*/
    void createWebtoonUserScore(long userId,long webtoonId,int score);

}
