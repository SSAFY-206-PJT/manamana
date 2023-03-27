package com.webtoon.manamana.webtoon.service.comment;

import com.webtoon.manamana.webtoon.dto.request.CommentRequestDTO;
import com.webtoon.manamana.webtoon.dto.response.Comment.CommentDTO;
import com.webtoon.manamana.webtoon.dto.response.Comment.CommentListDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WebtoonCommentService {


    /*작품 댓글 전체 조회*/
    List<CommentListDTO> findCommentAll(long userId, long webtoonId, Pageable pageable);

    /*작품 댓글 작성*/
    CommentDTO createComment(long userId, long webtoonId, CommentRequestDTO commentRequestDTO);

    /*작품 댓글 수정*/
    CommentDTO updateComment(long userId, long webtoonId, long commentId, CommentRequestDTO commentRequestDTO);

    /*작품 댓글 삭제*/
    void removeComment(long userId, long webtoonId, List<Long> ids);
}
