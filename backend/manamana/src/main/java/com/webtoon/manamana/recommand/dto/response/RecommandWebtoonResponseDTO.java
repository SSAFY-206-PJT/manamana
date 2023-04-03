package com.webtoon.manamana.recommand.dto.response;

import com.webtoon.manamana.recommand.dto.request.ApiAuthorDTO;
import com.webtoon.manamana.webtoon.dto.response.AuthorDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecommandWebtoonResponseDTO {

    private long id;
    private String name;
    private String imagePath;
    private List<ApiAuthorDTO> authors;

    @Builder
    public RecommandWebtoonResponseDTO(long id, String name, String imagePath, List<ApiAuthorDTO> authors) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.authors = authors;
    }
}