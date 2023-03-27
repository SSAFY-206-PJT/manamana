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
public class RecommandWebtoonResponseDTO {

    private List<ApiResponseDTO> result;

    @Builder
    public RecommandWebtoonResponseDTO(List<ApiResponseDTO> result) {
        this.result = result;
    }
}
