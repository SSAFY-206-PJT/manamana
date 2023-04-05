package com.manamana.crawling.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseRecommendDataDTO {
    private List<UserWebtoonDTO> data;

    @Builder
    public ResponseRecommendDataDTO(List<UserWebtoonDTO> data) {
        this.data = data;
    }

    public static ResponseRecommendDataDTO createDTO(List<UserWebtoonDTO> userWebtoonDTOList) {
        return ResponseRecommendDataDTO.builder()
                .data(userWebtoonDTOList)
                .build();
    }
}
