package com.webtoon.manamana.recommand.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecommendDataRequestDTO {

    private List<RecommendApiRequestDTO> recommendApiRequestDTOS;
    private List<Long> userLikedWebtoon;

    @Builder
    public RecommendDataRequestDTO(List<RecommendApiRequestDTO> recommendApiRequestDTOS, List<Long> userLikedWebtoon) {
        this.recommendApiRequestDTOS = recommendApiRequestDTOS;
        this.userLikedWebtoon = userLikedWebtoon;
    }
}
