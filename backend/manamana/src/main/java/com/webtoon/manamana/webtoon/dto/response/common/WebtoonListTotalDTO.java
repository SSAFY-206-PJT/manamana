package com.webtoon.manamana.webtoon.dto.response.common;

//최종적으로 리턴되는 형태

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WebtoonListTotalDTO {

    private long count;

    private List<WebtoonListDTO> contents;

    @Builder
    public WebtoonListTotalDTO(long count, List<WebtoonListDTO> contents) {
        this.count = count;
        this.contents = contents;
    }


    public static WebtoonListTotalDTO createDTO(long count, List<WebtoonListDTO> webtoonListDTO){

        return WebtoonListTotalDTO.builder()
                .count(count)
                .contents(webtoonListDTO)
                .build();
    }
}
