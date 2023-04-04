package com.manamana.crawling.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WebtoonUpdateDTO {
    private List<Long> data;

    @Builder
    public WebtoonUpdateDTO(List<Long> data) {
        this.data = data;
    }

    public static WebtoonUpdateDTO createDTO(List<Long> data) {
        return WebtoonUpdateDTO.builder()
                .data(data)
                .build();
    }
}
