package com.webtoon.manamana.recommand.service;

import com.webtoon.manamana.recommand.dto.request.WorldCupRequestDTO;
import com.webtoon.manamana.recommand.dto.response.ApiResponseDTO;
import com.webtoon.manamana.recommand.dto.response.RecommandWebtoonResponseDTO;
import com.webtoon.manamana.recommand.dto.response.WorldCupResponseDTO;
import com.webtoon.manamana.recommand.dto.response.WorldCupResultDTO;

import java.util.List;

public interface RecommandService {

    List<RecommandWebtoonResponseDTO> recommandUserWebtoon() throws Exception;

    List<RecommandWebtoonResponseDTO> recommandAssociationWebtoon(long webtoonId) throws Exception;

    List<WorldCupResponseDTO> worldCupWebtoonSearch();

    WorldCupResultDTO worldCupWebtoonSave(WorldCupRequestDTO worldCupRequestDTO);
}
