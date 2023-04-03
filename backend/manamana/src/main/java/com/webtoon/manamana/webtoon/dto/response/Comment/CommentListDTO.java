package com.webtoon.manamana.webtoon.dto.response.comment;

import com.webtoon.manamana.entity.webtoon.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentListDTO {

    private long id;
    private String content;
    private boolean isSpoiler;
    private long report;
    private String createTime;
    private UserInfoDTO user;

    @Builder
    public CommentListDTO(long id, String content, boolean isSpoiler, long report, String createTime, UserInfoDTO user) {
        this.id = id;
        this.content = content;
        this.isSpoiler = isSpoiler;
        this.report = report;
        this.createTime = createTime;
        this.user = user;
    }

    public static CommentListDTO createDTO(Comment comment){

        return CommentListDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .isSpoiler(comment.isSpoiler())
                .report(comment.getReport())
                .createTime(comment.getCreateTime().toString())
                .user(UserInfoDTO.createDTO(comment.getUser())).build();
    }
}
