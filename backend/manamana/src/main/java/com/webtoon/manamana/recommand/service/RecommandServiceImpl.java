package com.webtoon.manamana.recommand.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtoon.manamana.recommand.dto.request.ApiAuthorDTO;
import com.webtoon.manamana.recommand.dto.request.AssosiationApiRequestDTO;
import com.webtoon.manamana.recommand.dto.request.RecommandWebtoonRequestDTO;
import com.webtoon.manamana.recommand.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommandServiceImpl implements RecommandService {

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

    @Override
    public  List<RecommandWebtoonResponseDTO> recommandAssociationWebtoon() throws Exception {

        /*
            TODO : DB users_and_webtoons 테이블에서 모든 정보 가져와야함, Exception 던졌던거 처리
         */

        List<AssosiationApiRequestDTO> assosiationApiRequestDTOS = new ArrayList<>();

        /* 테스트용 */
        assosiationApiRequestDTOS.add(
                AssosiationApiRequestDTO.builder()
                        .userId(1L)
                        .webtoonId(1L)
                        .score(5)
                        .build()
        );

        assosiationApiRequestDTOS.add(
                AssosiationApiRequestDTO.builder()
                        .userId(2L)
                        .webtoonId(2L)
                        .score(3)
                        .build()
        );

        assosiationApiRequestDTOS.add(
                AssosiationApiRequestDTO.builder()
                        .userId(3L)
                        .webtoonId(1L)
                        .score(1)
                        .build()
        );
        /* 테스트용 */

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        ObjectMapper objectMapper = new ObjectMapper();

        String request = objectMapper.writeValueAsString(assosiationApiRequestDTOS);

        HttpEntity entity = new HttpEntity(request, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        // url 바꿔야함
        ResponseEntity<String> response = restTemplate.exchange("http://127.0.0.1:8000/test", HttpMethod.POST, entity, String.class);

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

            /* 테스트용 데이터 */
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
            /* 테스트용 데이터 */

        }

        return recommandWebtoonResponseDTOS;
    }

    @Override
    public List<WorldCupResponseDTO> worldCupWebtoonSearch() {

        /*
            TODO : DB 접근 로직 필요.
            TODO : DB에서 어떤 웹툰 정보를 가져와야하는지 고민해봐야함
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
