package com.webtoon.manamana.util.dto.response;

import com.webtoon.manamana.entity.webtoon.codetable.DayCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayResponseDTO {

    private int id;
    private String day;

    @Builder
    public DayResponseDTO(int id, String day) {
        this.id = id;
        this.day = day;
    }

    public static DayResponseDTO createDTO(DayCode dayCode){
        return DayResponseDTO.builder()
                .id(dayCode.getId())
                .day(dayCode.getDay()).build();
    }
}
