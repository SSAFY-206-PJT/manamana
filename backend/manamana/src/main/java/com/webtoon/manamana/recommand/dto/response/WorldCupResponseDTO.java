package com.webtoon.manamana.recommand.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorldCupResponseDTO {

    private long id;
    private String imagePath;

    @Builder
    public WorldCupResponseDTO(long id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }
}
