package com.webtoon.manamana.recommand.dto.response;

import com.webtoon.manamana.webtoon.dto.response.AuthorDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponseDTO {

    private long id;
    private String name;
    private String imagePath;
    private List<AuthorDTO> authors;

    @Builder
    public ApiResponseDTO(long id, String name, String imagePath, List<AuthorDTO> authors) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.authors = authors;
    }
}
