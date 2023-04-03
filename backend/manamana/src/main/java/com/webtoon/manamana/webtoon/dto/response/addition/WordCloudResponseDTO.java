package com.webtoon.manamana.webtoon.dto.response.addition;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordCloudResponseDTO {

    private long count; //단어 개수.
    private String context; //단어

    @Builder
    public WordCloudResponseDTO(long count, String context) {
        this.count = count;
        this.context = context;
    }

    public static WordCloudResponseDTO createDTO(String key, long value){

        return WordCloudResponseDTO.builder()
                .count(value)
                .context(key).build();

    }
}
