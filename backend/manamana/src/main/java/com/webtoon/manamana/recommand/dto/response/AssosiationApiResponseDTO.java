package com.webtoon.manamana.recommand.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssosiationApiResponseDTO {

    private List<AssosiationWebtoonResponseDTO> result;
}
