package com.webtoon.manamana.webtoon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayDTO {

    private int id;
    private int codeId;

    @Builder
    public DayDTO(int id, int codeId) {
        this.id = id;
        this.codeId = codeId;
    }
}
