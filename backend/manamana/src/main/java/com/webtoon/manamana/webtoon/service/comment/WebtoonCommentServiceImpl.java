package com.webtoon.manamana.webtoon.service.comment;

import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.webtoon.Comment;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.user.repository.user.UserRepository;
import com.webtoon.manamana.webtoon.dto.request.CommentRequestDTO;
import com.webtoon.manamana.webtoon.dto.response.comment.CommentDTO;
import com.webtoon.manamana.webtoon.dto.response.comment.CommentListDTO;
import com.webtoon.manamana.webtoon.repository.comment.CommentRepository;
import com.webtoon.manamana.webtoon.repository.comment.CommentRepositorySupport;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.webtoon.manamana.config.response.exception.CustomExceptionStatus.*;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class WebtoonCommentServiceImpl implements WebtoonCommentService{

    private final WebtoonRepository webtoonRepository;
    private final CommentRepository commentRepository;
    private final CommentRepositorySupport commentRepositorySupport;
    private final UserRepository userRepository;

    /*댓글 전체 조회*/
    @Override
    public List<CommentListDTO> findCommentAll(long userId, long webtoonId, Pageable pageable) {

        //웹툰 조회
        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_WEBTOON));

        //댓글 조회
        List<Comment> comments = commentRepositorySupport.findCommentAll(webtoonId, pageable);

        //DTO 변환
        List<CommentListDTO> commentListDTOS = comments.stream()
                .map(CommentListDTO::createDTO)
                .collect(Collectors.toList());

        return commentListDTOS;
    }

    /*댓글 생성*/
    @Override
    public CommentDTO createComment(long userId, long webtoonId, CommentRequestDTO commentRequestDTO) {

        //유저 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_USER));


        //웹툰 조회
        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_WEBTOON));


        //댓글 생성 및 저장.
        Comment comment = Comment.createComment(user, webtoon, commentRequestDTO);
        Comment saveComment = commentRepository.save(comment);


        //DTO 변환
        CommentDTO commentDTO = CommentDTO.createDTO(saveComment);

        return commentDTO;
    }

    /*댓글 수정*/
    @Override
    public CommentDTO updateComment(long userId, long webtoonId, long commentId, CommentRequestDTO commentRequestDTO) {

        //유저 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_USER));

        //웹툰 조회
        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_WEBTOON));

        //댓글 조회
        Comment comment = commentRepositorySupport.findCommentById(webtoonId, userId, commentId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_COMMENT));

        //수정
        comment.updateComment(commentRequestDTO);

        //DTO 변환
        CommentDTO commentDTO = CommentDTO.createDTO(comment);

        return commentDTO;

    }

    /*댓글 삭제*/
    @Override
    public void removeComment(long userId, long webtoonId, List<Long> ids) {

        //유저 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_USER));

        //웹툰 조회
        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_WEBTOON));

        ids.forEach(id -> {
            //댓글 조회
            Comment comment = commentRepositorySupport.findCommentById(webtoonId, userId, id)
                    .orElseThrow(() -> new CustomException(NOT_FOUNT_COMMENT));

            //댓글 삭제.
            comment.removeComment();
        });
    }
}
