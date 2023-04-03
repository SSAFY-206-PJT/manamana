package com.webtoon.manamana.recommand.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiAuthorDTO {

    private int id;
    private String name;

    @Builder
    public ApiAuthorDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
