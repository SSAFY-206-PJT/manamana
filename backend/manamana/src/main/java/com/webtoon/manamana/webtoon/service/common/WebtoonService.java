package com.webtoon.manamana.webtoon.service.common;

import com.webtoon.manamana.entity.webtoon.WebtoonProvider;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonDetailDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonListDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonProviderDTO;
import com.webtoon.manamana.webtoon.util.WebtoonListFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WebtoonService {

    /*웹툰 전체 조회*/
    List<WebtoonListDTO> findWebtoonAll(WebtoonListFilter webtoonListFilter, Pageable pageable);

    /*웹툰 상세 조회*/
    WebtoonDetailDTO findWebtoonOne(long userId, long webtoonId);

    /*웹툰 플랫폼 정보*/
    WebtoonProviderDTO findWebtoonProviderAll(long webtoonId);
}
