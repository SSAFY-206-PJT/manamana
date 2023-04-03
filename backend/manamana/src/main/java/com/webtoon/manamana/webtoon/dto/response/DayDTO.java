package com.webtoon.manamana.webtoon.dto.response;

import com.webtoon.manamana.entity.webtoon.WebtoonDay;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayDTO {

    private long id;
    private int codeId;

    @Builder
    public DayDTO(long id, int codeId) {
        this.id = id;
        this.codeId = codeId;
    }

    public static DayDTO createDTO(WebtoonDay webtoonDay){

        return DayDTO.builder()
                .id(webtoonDay.getId())
                .codeId(webtoonDay.getCodeId()).build();
    }
}
