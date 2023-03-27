package com.webtoon.manamana.webtoon.service.addition;

import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.webtoon.Comment;
import com.webtoon.manamana.entity.webtoon.Report;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.user.repository.user.UserRepository;
import com.webtoon.manamana.user.repository.user.UserRepositorySupport;
import com.webtoon.manamana.webtoon.dto.request.ScoreRequestDTO;
import com.webtoon.manamana.webtoon.repository.comment.CommentReportRepository;
import com.webtoon.manamana.webtoon.repository.comment.CommentReportRepositorySupport;
import com.webtoon.manamana.webtoon.repository.comment.CommentRepository;
import com.webtoon.manamana.webtoon.repository.comment.CommentRepositorySupport;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepository;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.webtoon.manamana.config.response.exception.CustomExceptionStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class WebtoonAdditionServiceImpl implements WebtoonAdditionService{

    private final WebtoonRepository webtoonRepository;
    private final WebtoonRepositorySupport webtoonRepositorySupport;

    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;

    private final CommentReportRepository commentReportRepository;
    private final CommentReportRepositorySupport commentReportRepositorySupport;

    private final CommentRepository commentRepository;
    private final CommentRepositorySupport commentRepositorySupport;

    /*작품 댓글 신고기능*/
    @Override
    public void commentReport(long userId,long webtoonId, long commentId) {

        //유저 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_USER));

        //작품 조회
        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_WEBTOON));

        //댓글 조회 - 삭제되지 않았고, 내 댓글이 아닌것 조회
        Comment comment = commentRepositorySupport.findCommentNotUser(userId, commentId)
                .orElseThrow(() -> new CustomException(BAD_COMMENT_REQUEST));

        //신고테이블 조회 - 값이 있으면 예외, 없으면 새로 생성.
        commentReportRepositorySupport.findReportByUserAndReport(userId, commentId)
                .ifPresentOrElse(report -> new CustomException(ALREADY_REPORT_COMMENT),
                        () -> commentReportRepository.save(Report.createReport(userId,comment)));


        //댓글테이블에 신고 횟수 증가.
        comment.commentReport();
    }

    /*작품 관심 등록*/
    @Override
    public void createLikeWebtoon(long userId,long webtoonId) {

        //유저 조회

        //작품 조회

        //관심등록 테이블 조회

        //없으면 생성

        //있으면 like 값 확인해서 업데이트.

    }

    /*개인이 평가한 평점*/
    @Override
    public ScoreRequestDTO getWebtoonUserScore(long userId,long webtoonId) {

        //유저 조회

        //작품 조회

        //유저 작품 연계 테이블 조회


        return null;
    }

    /*작품 평점 생성*/
    @Override
    public void createWebtoonUserScore(long userId,long webtoonId,int score) {

        //유저 조회

        //작품 조회

        //유저 작품 연계 테이블 조회

        //값이 없으면 입력한 평점으로 새로 생성

        //값이 있으면 생성한 값으로 변경

    }

    /*작품 평점 수정*/
    @Override
    public void updateWebtoonUserScore(long userId,long webtoonId,int score) {

        //유저 조회

        //작품 조회

        //유저 작품 연계 테이블 조회

        // 기존에 있는 값 수정

    }
}
