package com.webtoon.manamana.recommand.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssosiationWebtoonResponseDTO {

    private long webtoonId;

    @Override
    public String toString() {
        return "AssosiationWebtoonResponseDTO{" +
                "webtoonId=" + webtoonId +
                '}';
    }
}
