package com.webtoon.manamana.webtoon.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentDeleteDTO {

    private List<Long> id;
}
