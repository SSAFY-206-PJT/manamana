package com.webtoon.manamana.webtoon.service;

import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.entity.webtoon.WebtoonGenre;
import com.webtoon.manamana.entity.webtoon.WebtoonProvider;
import com.webtoon.manamana.entity.webtoon.codetable.Genre;
import com.webtoon.manamana.entity.webtoon.codetable.Grade;
import com.webtoon.manamana.entity.webtoon.codetable.SerialStatus;
import com.webtoon.manamana.util.repository.*;
import com.webtoon.manamana.webtoon.dto.response.GenreDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonDetailDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonListDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonProviderDTO;
import com.webtoon.manamana.webtoon.repository.WebtoonGenreRepositorySupport;
import com.webtoon.manamana.webtoon.repository.WebtoonRepository;
import com.webtoon.manamana.webtoon.repository.WebtoonRepositorySupport;
import com.webtoon.manamana.webtoon.util.WebtoonListFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class WebtoonServiceImpl implements WebtoonService{

    private final WebtoonRepository webtoonRepository;
    private final WebtoonRepositorySupport webtoonRepositorySupport;
    private final StatusCodeRepository statusCodeRepository;
    private final WebtoonProviderRepository webtoonProviderRepository;
    private final GradeCodeRepository gradeCodeRepository;
    private final WebtoonGenreRepositorySupport webtoonGenreRepositorySupport;
    /*웹툰 전체 조회*/
    @Override
    public List<WebtoonListDTO> findWebtoonAll(WebtoonListFilter webtoonListFilter, Pageable pageable) {

        List<Webtoon> webtoonAll = webtoonRepositorySupport.findWebtoonAll(webtoonListFilter, pageable);

        //웹툰 연재 상태 코드표 조회
        List<SerialStatus> serialStatuses = statusCodeRepository.findAll();
        Map<Integer,String> statusMap = new HashMap<>();

        serialStatuses.forEach(serialStatus -> {
            statusMap.put(serialStatus.getId(),serialStatus.getStatus());
        });

        List<WebtoonListDTO> webtoonListDTOS = webtoonAll.stream()
                .map(webtoon -> WebtoonListDTO.createDTO(webtoon, statusMap))
                .collect(Collectors.toList());

        return webtoonListDTOS;
    }

    /*웹툰 상세 조회*/
    @Override
    public WebtoonDetailDTO findWebtoonOne(long userId,long webtoonId) {


        //웹툰 조회.
        Webtoon webtoon = webtoonRepositorySupport.findWebtoonOne(userId, webtoonId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_WEBTOON));

        //웹툰 연재 상태 코드표 조회
        List<SerialStatus> serialStatuses = statusCodeRepository.findAll();
        Map<Integer,String> statusMap = new HashMap<>();

        serialStatuses.forEach(serialStatus -> {
            statusMap.put(serialStatus.getId(),serialStatus.getStatus());
        });

        //웹툰 연령등급 코드표 조회.
        List<Grade> grades = gradeCodeRepository.findAll();
        Map<Integer,String> gradeMap = new HashMap<>();

        grades.forEach(grade -> {
            gradeMap.put(grade.getId(),grade.getGrade());
        });

        //장르 조회
        List<WebtoonGenre> webtoonGenres = webtoonGenreRepositorySupport.findGenrebyWebtoonAll(webtoonId);
        List<GenreDTO> genreDTOS = webtoonGenres.stream()
                .map(GenreDTO::createDTO)
                .collect(Collectors.toList());

        //출력한 DTO로 변환
        WebtoonDetailDTO webtoonDetailDTO = WebtoonDetailDTO.createDTO(webtoon, genreDTOS, statusMap, gradeMap);

        return webtoonDetailDTO;
    }

    /*웹툰 플랫폼 정보*/
    // TODO : 현재는 디비 구조를 잘못해서 하나만 있지만, 추후에 디비 구조를 개편하고 여러 플랫폼도 처리할 수 있도록 해야됨.
    @Override
    public WebtoonProviderDTO findWebtoonProviderAll(long webtoonId) {

        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_WEBTOON));

        WebtoonProvider webtoonProvider = webtoonProviderRepository.findById(webtoon.getProviderId().getId())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BAD_PROVIDER_REQUEST));

        WebtoonProviderDTO webtoonProviderDTO = WebtoonProviderDTO.createDTO(webtoonProvider);

        return webtoonProviderDTO;
    }
}
