package com.webtoon.manamana.recommand.service;

import com.webtoon.manamana.recommand.dto.response.ApiResponseDTO;
import com.webtoon.manamana.recommand.dto.response.RecommandWebtoonResponseDTO;

import java.util.List;

public interface RecommandService {

    List<RecommandWebtoonResponseDTO> recommandUserWebtoon() throws Exception;

    List<RecommandWebtoonResponseDTO> recommandAssociationWebtoon() throws Exception;
}
