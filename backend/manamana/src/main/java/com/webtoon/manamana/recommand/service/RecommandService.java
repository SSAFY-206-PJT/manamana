package com.webtoon.manamana.recommand.service;

import com.webtoon.manamana.recommand.dto.response.RecommandWebtoonResponseDTO;

public interface RecommandService {

    RecommandWebtoonResponseDTO recommandUserWebtoon() throws Exception;
}
