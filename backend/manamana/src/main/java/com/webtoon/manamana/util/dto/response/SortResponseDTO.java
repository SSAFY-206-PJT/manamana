package com.webtoon.manamana.util.dto.response;

import com.webtoon.manamana.entity.webtoon.codetable.Sort;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortResponseDTO {
    private int id;
    private String name;

    @Builder
    public SortResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SortResponseDTO createDTO(Sort sort){
        return SortResponseDTO.builder()
                .id(sort.getId())
                .name(sort.getName()).build();
    }
}
