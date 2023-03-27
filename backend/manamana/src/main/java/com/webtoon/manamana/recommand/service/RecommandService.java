package com.webtoon.manamana.recommand.service;

import com.webtoon.manamana.recommand.dto.response.ApiResponseDTO;
import com.webtoon.manamana.recommand.dto.response.RecommandWebtoonResponseDTO;

import java.util.List;

public interface RecommandService {

    List<ApiResponseDTO> recommandUserWebtoon() throws Exception;
}
