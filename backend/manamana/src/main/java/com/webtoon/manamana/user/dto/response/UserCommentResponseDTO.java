package com.webtoon.manamana.user.dto.response;

/*유저가 작성한 댓글 정보조회DTO*/

import com.webtoon.manamana.entity.webtoon.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserCommentResponseDTO {

    private long id;
    private String content;
    private String createTime;
    private CommentWebtoonInfoDTO webtoon;

    @Builder
    public UserCommentResponseDTO(long id, String content, String createTime, CommentWebtoonInfoDTO webtoon) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.webtoon = webtoon;
    }

    //Entity -> DTO
    public static UserCommentResponseDTO createDTO(Comment comment){


        return UserCommentResponseDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createTime(comment.getCreateTime().toString())
                .webtoon(CommentWebtoonInfoDTO.createDTO(comment.getWebtoon())).build();
    }
}
