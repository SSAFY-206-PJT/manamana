package com.webtoon.manamana.webtoon.service.addition;

import com.webtoon.manamana.webtoon.dto.request.ScoreRequestDTO;

public interface WebtoonAdditionService {

    /*작품 댓글 신고기능*/
    void commentReport(long userId,long webtoonId, long commentId);

    /*작품 관심 등록*/
    void createLikeWebtoon(long userId,long webtoonId);

    /*댓글 워드 클라우드*/
    //TODO : 워드클라우드 기능은 보류

    /*개인이 평가한 평점*/
    ScoreRequestDTO getWebtoonUserScore(long userId,long webtoonId);

    /*작품 평점 생성*/
    void createWebtoonUserScore(long userId,long webtoonId,int score);

    /*작품 평점 수정*/
    void updateWebtoonUserScore(long userId,long webtoonId,int score);
}
