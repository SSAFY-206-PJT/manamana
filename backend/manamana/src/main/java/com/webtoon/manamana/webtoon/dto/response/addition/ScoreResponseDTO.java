package com.webtoon.manamana.webtoon.dto.response.addition;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreResponseDTO {

    private int score;

    @Builder
    public ScoreResponseDTO(int score) {
        this.score = score;
    }

    public static ScoreResponseDTO createDTO(int score){
        return ScoreResponseDTO.builder()
                .score(score).build();
    }
}
