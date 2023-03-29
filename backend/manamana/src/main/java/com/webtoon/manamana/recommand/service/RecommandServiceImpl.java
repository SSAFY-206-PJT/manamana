package com.webtoon.manamana.recommand.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.UserWebtoon;
import com.webtoon.manamana.entity.webtoon.Author;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.recommand.dto.request.ApiAuthorDTO;
import com.webtoon.manamana.recommand.dto.request.AssosiationApiRequestDTO;
import com.webtoon.manamana.recommand.dto.request.RecommandWebtoonRequestDTO;
import com.webtoon.manamana.recommand.dto.request.WorldCupRequestDTO;
import com.webtoon.manamana.recommand.dto.response.*;
import com.webtoon.manamana.user.repository.user.UserWebtoonRepository;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepository;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommandServiceImpl implements RecommandService {

    private final UserWebtoonRepository userWebtoonRepository;
    private final WebtoonRepository webtoonRepository;
    private final WebtoonRepositorySupport webtoonRepositorySupport;

    /* 추천 알고리즘을 통한 웹툰 조회 */
    @Override
    public List<RecommandWebtoonResponseDTO> recommandUserWebtoon() throws Exception {

        /*
            TODO : DB에서 데이터 가져오는 로직, Exception 던졌던거 다시 처리
         */


        /* 테스트용 */
        List<RecommandWebtoonRequestDTO> recommandWebtoonRequestDTOS = new ArrayList<>();

        recommandWebtoonRequestDTOS.add(
                RecommandWebtoonRequestDTO.builder()
                        .id(1)
                        .name("1초")
                        .grade("전체이용가")
                        .status("연재중")
                        .authors("시니")
                        .genres("드라마")
                        .days("금")
                        .build()
        );

        recommandWebtoonRequestDTOS.add(
                RecommandWebtoonRequestDTO.builder()
                        .id(2)
                        .name("상남자")
                        .grade("전체이용가")
                        .status("연재중")
                        .authors("하늘소")
                        .genres("드라마")
                        .days("금")
                        .build()
        );
        /* 테스트용 */

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        ObjectMapper objectMapper = new ObjectMapper();

        String request = objectMapper.writeValueAsString(recommandWebtoonRequestDTOS);

        HttpEntity entity = new HttpEntity(request, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        // url 바꿔야함
        ResponseEntity<String> response = restTemplate.exchange("http://127.0.0.1:8000/test", HttpMethod.POST, entity, String.class);

//        System.out.println(response.getStatusCode());
//        log.info(response.getBody());

        List<RecommandWebtoonResponseDTO> recommandWebtoonResponseDTOS = objectMapper.readValue(response.getBody(), ApiResponseDTO.class).getResult();

        return recommandWebtoonResponseDTOS;
    }

    /* 관련 웹툰 추천 */
    @Override
    public  List<RecommandWebtoonResponseDTO> recommandAssociationWebtoon() throws Exception {

        /*
            TODO : DB users_and_webtoons 테이블에서 모든 정보 가져와야함, Exception 던졌던거 처리
         */

        List<UserWebtoon> userWebtoons =  userWebtoonRepository.findAllByIsDeletedFalse();

        List<AssosiationApiRequestDTO> assosiationApiRequestDTOS = new ArrayList<>();

        for (UserWebtoon userWebtoon : userWebtoons) {
            assosiationApiRequestDTOS.add(
                    AssosiationApiRequestDTO.builder()
                            .userId(userWebtoon.getUser().getId())
                            .webtoonId(userWebtoon.getWebtoon().getId())
                            .score(userWebtoon.getScore())
                            .build()
            );
        }

        /* 테스트용
        assosiationApiRequestDTOS.add(
                AssosiationApiRequestDTO.builder()
                        .userId(1L)
                        .webtoonId(1L)
                        .score(3)
                        .build()
        );

        assosiationApiRequestDTOS.add(
                AssosiationApiRequestDTO.builder()
                        .userId(2L)
                        .webtoonId(2L)
                        .score(5)
                        .build()
        );

        assosiationApiRequestDTOS.add(
                AssosiationApiRequestDTO.builder()
                        .userId(3L)
                        .webtoonId(1L)
                        .score(1)
                        .build()
        );
        테스트용 */

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        ObjectMapper objectMapper = new ObjectMapper();

        String request = objectMapper.writeValueAsString(assosiationApiRequestDTOS);

        HttpEntity entity = new HttpEntity(request, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        // url 바꿔야함
        ResponseEntity<String> response = restTemplate.exchange("http://127.0.0.1:8000/assosiation", HttpMethod.POST, entity, String.class);

        List<AssosiationWebtoonResponseDTO> assosiationWebtoonResponseDTOS = objectMapper.readValue(response.getBody(), AssosiationApiResponseDTO.class).getResult();

        log.info(response.getBody());
        log.info(assosiationWebtoonResponseDTOS.toString());

        /*
            TODO : 추천된 webtoon id로 DB접근해서 webtoon 정보 반환
         */

        List<RecommandWebtoonResponseDTO> recommandWebtoonResponseDTOS = new ArrayList<>();

        for (AssosiationWebtoonResponseDTO assosiationWebtoonResponseDTO : assosiationWebtoonResponseDTOS) {

            /*
                TODO : webtoonId별 웹툰정보 DB에서 가져와야함
             */

            Webtoon webtoon = webtoonRepositorySupport.findWebtoonOne(assosiationWebtoonResponseDTO.getWebtoonId())
                    .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_WEBTOON));

            List<ApiAuthorDTO> apiAuthorDTOS = new ArrayList<>();

            for (Author author : webtoon.getAuthors()) {
                apiAuthorDTOS.add(
                        ApiAuthorDTO.builder()
                                .id(author.getId())
                                .name(author.getName())
                                .build()
                );
            }

            recommandWebtoonResponseDTOS.add(
                    RecommandWebtoonResponseDTO.builder()
                            .id(webtoon.getId())
                            .name(webtoon.getName())
                            .imagePath(webtoon.getImagePath())
                            .authors(apiAuthorDTOS)
                            .build()
            );

            /* 테스트용 데이터
            List<ApiAuthorDTO> apiAuthorDTOS = new ArrayList<>();

            apiAuthorDTOS.add(
                    ApiAuthorDTO.builder()
                            .id(1)
                            .name("시니")
                            .build()
            );

            apiAuthorDTOS.add(
                    ApiAuthorDTO.builder()
                            .id(2)
                            .name("광운")
                            .build()
            );

//            System.out.println(assosiationWebtoonResponseDTO.getWebtoonId());
            recommandWebtoonResponseDTOS.add(RecommandWebtoonResponseDTO.builder()
                    .id(assosiationWebtoonResponseDTO.getWebtoonId())
                    .name("1초")
                    .imagePath("image")
                    .authors(apiAuthorDTOS)
                    .build());
            테스트용 데이터 */

        }

        return recommandWebtoonResponseDTOS;
    }

    /* 취향 월드컵 조회 */
    @Override
    public List<WorldCupResponseDTO> worldCupWebtoonSearch() {

        /*
            TODO : DB 접근 로직 필요.
            TODO : 장르별 평점 TOP 10 중 랜덤 2개 뽑아서 반환
         */

        List<WorldCupResponseDTO> worldCupResponseDTOS = new ArrayList<>();

        worldCupResponseDTOS.add(
                WorldCupResponseDTO.builder()
                        .id(1L)
                        .imagePath("url1")
                        .build()
        );

        worldCupResponseDTOS.add(
                WorldCupResponseDTO.builder()
                        .id(2L)
                        .imagePath("url2")
                        .build()
        );

        return worldCupResponseDTOS;
    }

    /* 취향 월드컵 결과 저장 */
    @Override
    @Transactional
    public WorldCupResultDTO worldCupWebtoonSave(WorldCupRequestDTO worldCupRequestDTO) {

        log.info(worldCupRequestDTO.getId().toString());

        /*
            TODO : DB 접근 로직 필요
            TODO : 유저가 선택한 웹툰의 장르를 기반으로 해당 장르 평점 TOP 10 중 하나씩 뽑고, 그 중 1개 뽑아서 리턴 ?
         */

        WorldCupResultDTO worldCupResultDTO = WorldCupResultDTO.builder()
                .id(1L)
                .name("호랑이형님")
                .imagePath("https://image-comic.pstatic.net/webtoon/650305/thumbnail/thumbnail_IMAG21_3631086797392995425.jpg")
                .build();

        return worldCupResultDTO;
    }

    private static <T> ResponseEntity<String> springToFastAPI(T reqDTO, String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        ObjectMapper objectMapper = new ObjectMapper();

        String request = null;

        try {
            request = objectMapper.writeValueAsString(reqDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity entity = new HttpEntity(request, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        // url 바꿔야함
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response;
    }
}
