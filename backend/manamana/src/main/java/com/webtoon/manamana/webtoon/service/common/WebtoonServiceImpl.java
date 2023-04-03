package com.webtoon.manamana.webtoon.service.common;

import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.user.UserGenre;
import com.webtoon.manamana.entity.user.UserWebtoon;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.entity.webtoon.WebtoonAddition;
import com.webtoon.manamana.entity.webtoon.WebtoonGenre;
import com.webtoon.manamana.entity.webtoon.WebtoonProvider;
import com.webtoon.manamana.entity.webtoon.codetable.Grade;
import com.webtoon.manamana.entity.webtoon.codetable.SerialStatus;
import com.webtoon.manamana.user.repository.user.*;
import com.webtoon.manamana.util.repository.*;
import com.webtoon.manamana.webtoon.dto.response.GenreDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonDetailDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonListDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonProviderDTO;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonAdditionRepositorySupport;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonGenreRepositorySupport;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepository;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepositorySupport;
import com.webtoon.manamana.webtoon.util.WebtoonFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class WebtoonServiceImpl implements WebtoonService{

    private final WebtoonRepository webtoonRepository;
    private final WebtoonRepositorySupport webtoonRepositorySupport;
    private final StatusCodeRepository statusCodeRepository;
    private final WebtoonProviderRepository webtoonProviderRepository;
    private final GradeCodeRepository gradeCodeRepository;
    private final WebtoonGenreRepositorySupport webtoonGenreRepositorySupport;

    private final UserRepository userRepository;

    private final UserGenreRepository userGenreRepository;
    private final UserGenreRepositorySupport userGenreRepositorySupport;

    private final UserWebtoonRepositorySupport userWebtoonRepositorySupport;
    private final WebtoonAdditionRepositorySupport webtoonAdditionRepositorySupport;



    /*웹툰 전체 조회*/
    @Override
    public List<WebtoonListDTO> findWebtoonAll(WebtoonFilterDTO webtoonFilterDTO, Pageable pageable) {

        List<Webtoon> webtoonAll = webtoonRepositorySupport.findWebtoonAll(webtoonFilterDTO, pageable);

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

        //TODO : 유저id로 관심등록 했는지 확인.

        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_USER));

        //웹툰 조회.
        Webtoon webtoon = webtoonRepositorySupport.findWebtoonOne(webtoonId)
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

        //웹툰 상호작용정보 조회 - 조회수 증가를 위해.
        WebtoonAddition webtoonAddition = webtoonAdditionRepositorySupport.findAdditionByWebtoonId(webtoonId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_WEBTOON_ADDITION));

        //조회수 증가.
        webtoonAddition.updateViewCount();


        //관심등록 여부.
        Optional<UserWebtoon> userWebtoonOptional = userWebtoonRepositorySupport.findUserWetboonLikedByUserAndWebtoon(user, webtoon);

        boolean isLiked = false;
        if(userWebtoonOptional.isPresent()) isLiked = true;


        //출력한 DTO로 변환
        WebtoonDetailDTO webtoonDetailDTO = WebtoonDetailDTO.createDTO(webtoon, genreDTOS, isLiked,statusMap, gradeMap);

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

        WebtoonProviderDTO webtoonProviderDTO = WebtoonProviderDTO.createDTO(webtoonProvider, webtoon.getWebtoonUrl());

        return webtoonProviderDTO;
    }

    /*웹툰 보러가기시 가중치 증가(포함된 장르 증가.)*/
    @Override
    public void upToWeightWebtoon(long userId, long webtoonId) {

        //유저 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_USER));


        //해당 웹툰 조회
        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_WEBTOON));


        //웹툰에 속한 장르 조회
        List<WebtoonGenre> webtoonGenres = webtoonGenreRepositorySupport.findGenrebyWebtoonAll(webtoonId);


        //각 장르로 유저 장르 테이블 조회
        webtoonGenres.forEach(webtoonGenre -> {

            Optional<UserGenre> userGenreOptional = userGenreRepositorySupport.findUserGenre(user, webtoonGenre.getGenre());

            if(userGenreOptional.isPresent()){
                //해당하는 장르를 이전에 저장한 적 있으면 1증가.
                userGenreOptional.get().updateUserGenre(1);
            }

        });

    }
}
