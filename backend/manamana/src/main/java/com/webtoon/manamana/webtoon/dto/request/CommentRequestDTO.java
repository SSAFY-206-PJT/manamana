package com.webtoon.manamana.webtoon.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {

    private String content;

    private boolean isSpoiler;

    public boolean isSpoiler() {
        return isSpoiler;
    }
}
