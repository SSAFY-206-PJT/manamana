package com.webtoon.manamana.webtoon.dto.response.Comment;

import com.webtoon.manamana.entity.webtoon.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private long id;
    private String content;
    private boolean isSpoiler;

    @Builder
    public CommentDTO(long id, String content, boolean isSpoiler) {
        this.id = id;
        this.content = content;
        this.isSpoiler = isSpoiler;
    }

    public static CommentDTO createDTO(Comment comment){

        return CommentDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .isSpoiler(comment.isSpoiler()).build();

    }
}
