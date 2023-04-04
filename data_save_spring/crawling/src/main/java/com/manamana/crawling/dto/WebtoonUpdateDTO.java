package com.manamana.crawling.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WebtoonUpdateDTO {
    private List<Long> id;

    @Builder
    public WebtoonUpdateDTO(List<Long> id) {
        this.id = id;
    }

    public static WebtoonUpdateDTO createDTO(List<Long> id) {
        return WebtoonUpdateDTO.builder()
                .id(id)
                .build();
    }
}
