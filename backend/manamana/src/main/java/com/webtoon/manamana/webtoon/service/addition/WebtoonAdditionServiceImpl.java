package com.webtoon.manamana.webtoon.service.addition;

import com.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.user.UserGenre;
import com.webtoon.manamana.entity.user.UserWebtoon;
import com.webtoon.manamana.entity.webtoon.*;
import com.webtoon.manamana.user.repository.user.*;
import com.webtoon.manamana.webtoon.dto.request.ScoreRequestDTO;
import com.webtoon.manamana.webtoon.dto.response.addition.ScoreResponseDTO;
import com.webtoon.manamana.webtoon.repository.comment.CommentReportRepository;
import com.webtoon.manamana.webtoon.repository.comment.CommentReportRepositorySupport;
import com.webtoon.manamana.webtoon.repository.comment.CommentRepository;
import com.webtoon.manamana.webtoon.repository.comment.CommentRepositorySupport;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonAdditionRepositorySupport;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonGenreRepositorySupport;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepository;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.webtoon.manamana.config.response.exception.CustomExceptionStatus.*;

@Slf4j
@RequiredArgsConstructor
@Transactional
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

    private final UserWebtoonRepository userWebtoonRepository;
    private final UserWebtoonRepositorySupport userWebtoonRepositorySupport;

    private final WebtoonAdditionRepositorySupport webtoonAdditionRepositorySupport;

    private final UserGenreRepository userGenreRepository;
    private final UserGenreRepositorySupport userGenreRepositorySupport;

    private final WebtoonGenreRepositorySupport webtoonGenreRepositorySupport;


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
                .ifPresentOrElse(report -> {throw new CustomException(ALREADY_REPORT_COMMENT);},
                        () -> commentReportRepository.save(Report.createReport(userId,comment)));


        //댓글테이블에 신고 횟수 증가.
        comment.commentReport();
    }

    /*작품 관심 등록*/
    @Override
    public void createLikeWebtoon(long userId,long webtoonId) {

        //유저 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_USER));

        //작품 조회
        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_WEBTOON));

        //관심등록 테이블 조회 - 값이 있으면 isLiked 값을 확인해봐야 됨, 값이 없으면 추가
        userWebtoonRepositorySupport.findUserWebtoonByUserAndWebtoon(userId, webtoonId)
                .ifPresentOrElse(
                        userWebtoon -> {
                            if(userWebtoon.isLiked())  throw new CustomException(ALREADY_LIKE_WEBTOON);
                            else userWebtoon.updateLikedUserWebtoon();
                        },
                        () -> UserWebtoon.createLikeUserWebtoon(user,webtoon)
                );
    }

    /*개인이 평가한 평점*/
    @Override
    public ScoreResponseDTO getWebtoonUserScore(long userId, long webtoonId) {
        //유저 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_USER));

        //작품 조회
        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_WEBTOON));

        //리턴 할 DTO 객체
        ScoreResponseDTO scoreResponseDTO = null;

        //유저 작품 연계 테이블 조회
        UserWebtoon userWebtoon = userWebtoonRepositorySupport.findUserWebtoonByUserAndWebtoon(userId, webtoonId).get();

        if(userWebtoon != null) scoreResponseDTO = ScoreResponseDTO.createDTO(userWebtoon.getScore());
        else scoreResponseDTO = ScoreResponseDTO.createDTO(0);

        return scoreResponseDTO;
    }

    /*작품 평점 생성 및 수정*/
    @Override
    public void createWebtoonUserScore(long userId,long webtoonId,int score) {

        int[] scoreArray = {0,0,0,0,1,3};

        //유저 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_USER));

        //작품 조회
        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_WEBTOON));
        //작품 추가 정보 테이블 조회.
        WebtoonAddition webtoonAddition = webtoonAdditionRepositorySupport.findAdditionByWebtoonId(webtoonId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_WEBTOON_ADDITION));

        //웹툰에 해당하는 장르 조회
        List<WebtoonGenre> webtoonGenres = webtoonGenreRepositorySupport.findGenrebyWebtoonAll(webtoonId);


        //유저 작품 연계 테이블 조회
        Optional<UserWebtoon> userWebtoonOptional = userWebtoonRepositorySupport.findUserWebtoonByUserAndWebtoon(userId, webtoonId);

        //각 장르를 가지고 유저-장르 테이블을 조회해봄.
        webtoonGenres.forEach(webtoonGenre -> {
            //유저장르 연결 테이블 조회 - 가중치 누적을 위해서.
            Optional<UserGenre> userGenreOptional = userGenreRepositorySupport.findUserGenre(user, webtoonGenre.getGenre());

            //1,2,3점이면 패스, 4점이면 1, 5점이면 3을 추가함.
            UserGenre userGenre = null;
            //해당 장르가 있다면
            if(userGenreOptional.isPresent()){

                userGenre = userGenreOptional.get();
                // 유저-작품 연계테이블을 조회해서 이전에 몇점을 줬었는지 확인해야됨.
                if(userWebtoonOptional.isPresent()){

                    UserWebtoon userWebtoon = userWebtoonOptional.get();

                    //0이면 준적 없는 것이므로.
                    if(userWebtoon.getScore() == 0){
                        if(score == 4) userGenre.updateUserGenre(1);
                        else if(score == 5) userGenre.updateUserGenre(3);
                    }
                    //0이 아니면 이전에 평점을 줬었음.
                    else{
                        int oldScore = scoreArray[userWebtoon.getScore()];

                        if(score == 1 || score == 2 || score == 3){
                            userGenre.updateUserGenre(oldScore * (-1));
                        }
                        else if(score == 4){
                            userGenre.updateUserGenre(oldScore * (-1) + 1);
                        }
                        else{
                            userGenre.updateUserGenre(oldScore * (-1) + 3);
                        }
                    }
                }
                //이전에 평점을 준적이 없으면 그냥 더하면 됨.
                else{
                    if(score == 4) userGenre.updateUserGenre(1);
                    else if(score == 5) userGenre.updateUserGenre(3);
                }

            }
            //해당 장르가 없다면 - 점수안줌.
        });


        UserWebtoon userWebtoon = null;
        // 값이 있을떄,
        if(userWebtoonOptional.isPresent()){
            userWebtoon = userWebtoonOptional.get();
            //점수가 0인경우.
            if(userWebtoon.getScore() == 0){
                //평가한 사람수 증가
                webtoonAddition.updateScoreCount();

                //평가 누적점수 추가
                webtoonAddition.updateTotalScore(score);
                userWebtoon.updateScoreUserWebtoon(score);
            }
            //점수가 0이 아니면
            else {
                //점수 누적
                webtoonAddition.updateTotalScore(score - userWebtoon.getScore());
                // 기존에 있는 값 수정
                userWebtoon.updateScoreUserWebtoon(score);
            }
        }
        //값이 없을때
        else{
            log.info("check4");
            userWebtoon = UserWebtoon.createScoreUserWebtoon(user, webtoon, score);
            userWebtoonRepository.save(userWebtoon);
            webtoonAddition.updateTotalScore(score);
            webtoonAddition.updateScoreCount();
        }

    }

}
