package com.webtoon.manamana.recommand.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.TODO;
import com.webtoon.manamana.recommand.dto.request.RecommandWebtoonRequestDTO;
import com.webtoon.manamana.recommand.dto.response.RecommandWebtoonResponseDTO;
import com.webtoon.manamana.webtoon.dto.response.AuthorDTO;
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
    public RecommandWebtoonResponseDTO recommandUserWebtoon() throws Exception {

        /*
            TODO : DB에서 데이터 가져오는 로직, Exception 던졌던거 다시 처리
         */


        /* 테스트용 */
        /*List<RecommandWebtoonRequestDTO> recommandWebtoonRequestDTOS = new ArrayList<>();

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
        );*/
        /* 테스트용 */

        RecommandWebtoonRequestDTO recommandWebtoonRequestDTO
                = RecommandWebtoonRequestDTO.builder()
                .id(1)
                .name("1초")
                .grade("전체이용가")
                .status("연재중")
                .authors("시니")
                .genres("드라마")
                .days("금")
                .build();


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        ObjectMapper objectMapper = new ObjectMapper();

        String request = objectMapper.writeValueAsString(recommandWebtoonRequestDTO);

        HttpEntity entity = new HttpEntity(request, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        // url 바꿔야함
        ResponseEntity<String> response = restTemplate.exchange("http://127.0.0.1:8000/test", HttpMethod.POST, entity, String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        RecommandWebtoonResponseDTO recommandWebtoonResponseDTO = objectMapper.readValue(response.getBody(), RecommandWebtoonResponseDTO.class);

        return recommandWebtoonResponseDTO;
    }
}
