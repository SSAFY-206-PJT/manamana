package com.webtoon.manamana.recommand.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtoon.manamana.recommand.dto.request.AssosiationApiRequestDTO;
import com.webtoon.manamana.recommand.dto.request.RecommandWebtoonRequestDTO;
import com.webtoon.manamana.recommand.dto.response.ApiResponseDTO;
import com.webtoon.manamana.recommand.dto.response.AssosiationApiResponseDTO;
import com.webtoon.manamana.recommand.dto.response.AssosiationWebtoonResponseDTO;
import com.webtoon.manamana.recommand.dto.response.RecommandWebtoonResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

        String request = objectMapper.writeValueAsString(recommandWebtoonRequestDTOS); //
//        System.out.println(request);

        HttpEntity entity = new HttpEntity(request, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        // url 바꿔야함
        ResponseEntity<String> response = restTemplate.exchange("http://127.0.0.1:8000/test", HttpMethod.POST, entity, String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        List<RecommandWebtoonResponseDTO> recommandWebtoonResponseDTOS = objectMapper.readValue(response.getBody(), ApiResponseDTO.class).getResult();

        return recommandWebtoonResponseDTOS;
    }

    @Override
    public void recommandAssociationWebtoon() throws Exception {

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
                        .score(5)
                        .build()
        );

        assosiationApiRequestDTOS.add(
                AssosiationApiRequestDTO.builder()
                        .userId(3L)
                        .webtoonId(1L)
                        .score(2)
                        .build()
        );
        /* 테스트용 */

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        ObjectMapper objectMapper = new ObjectMapper();

        String request = objectMapper.writeValueAsString(assosiationApiRequestDTOS); //

        HttpEntity entity = new HttpEntity(request, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        // url 바꿔야함
        ResponseEntity<String> response = restTemplate.exchange("http://127.0.0.1:8000/test", HttpMethod.POST, entity, String.class);

//        List<ApiResponseDTO> apiResponseDTOS = objectMapper.readValue(response.getBody(), RecommandWebtoonResponseDTO.class).getResult();
        List<AssosiationWebtoonResponseDTO> assosiationWebtoonResponseDTOS = objectMapper.readValue(response.getBody(), AssosiationApiResponseDTO.class).getResult();

        /*
            TODO : 추천된 webtoon id로 DB접근해서 webtoon 정보 반환
         */

        return ;
    }
}
