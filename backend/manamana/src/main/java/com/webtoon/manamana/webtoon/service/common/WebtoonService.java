package com.webtoon.manamana.webtoon.service.common;

import com.webtoon.manamana.webtoon.dto.response.common.WebtoonDetailDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonListDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonListTotalDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonProviderDTO;
import com.webtoon.manamana.webtoon.util.WebtoonFilterDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WebtoonService {

    /*웹툰 전체 조회*/
    WebtoonListTotalDTO findWebtoonAll(WebtoonFilterDTO webtoonFilterDTO, Pageable pageable);

    /*웹툰 상세 조회*/
    WebtoonDetailDTO findWebtoonOne(long userId, long webtoonId);

    /*웹툰 플랫폼 정보*/
    WebtoonProviderDTO findWebtoonProviderAll(long webtoonId);

    /*웹툰 보러가기시 가중치 증가(포함된 장르 증가.)*/
    void upToWeightWebtoon(long userId,long webtoonId);
}
