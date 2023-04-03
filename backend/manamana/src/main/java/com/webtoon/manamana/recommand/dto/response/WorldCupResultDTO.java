package com.webtoon.manamana.recommand.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorldCupResultDTO {

    private long id;
    private String name;
    private String imagePath;

    @Builder
    public WorldCupResultDTO(long id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }
}
