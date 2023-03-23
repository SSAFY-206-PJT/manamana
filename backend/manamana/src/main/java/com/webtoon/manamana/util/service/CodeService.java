package com.webtoon.manamana.util.service;

import com.webtoon.manamana.util.dto.response.*;
import com.webtoon.manamana.util.repository.DayCodeRepository;

import java.util.List;

public interface CodeService {

    /*요일 정보 조회*/
    List<DayResponseDTO> getDays();

    /*장르 정보 조회*/
    List<GenreResponseDTO> getGenres();

    /*등급 정보 조회*/
    List<GradeResponseDTO> getGrades();

    /*정렬 정보 조회*/
    List<SortResponseDTO> getSorts();

    /*연재 상태 코드 조회*/
    List<StatusResponseDTO> getStatuses();

    /*웹툰 제공자 정보 조회*/
    List<WebtoonProviderResponseDTO> providers();

    // TODO : 네이버 api와 연동해서 적용해야 됨.
    /*웹툰 키워드 top 10*/
}
