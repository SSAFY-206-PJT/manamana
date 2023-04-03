package com.webtoon.manamana.util.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtoon.manamana.config.ApiKeyProperty;
import com.webtoon.manamana.entity.webtoon.WebtoonProvider;
import com.webtoon.manamana.entity.webtoon.codetable.*;
import com.webtoon.manamana.util.dto.naver.KeywordRankResponseDTO;
import com.webtoon.manamana.util.dto.response.*;
import com.webtoon.manamana.util.repository.*;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{

    private final DayCodeRepository dayCodeRepository;
    private final GenreCodeRepository genreCodeRepository;
    private final GradeCodeRepository gradeCodeRepository;
    private final SortCodeRepository sortCodeRepository;
    private final StatusCodeRepository statusCodeRepository;

    private final WebtoonProviderRepository webtoonProviderRepository;

    private final ApiKeyProperty apiKeyProperty;

    private final ObjectMapper objectMapper;

    @Override
    public List<DayResponseDTO> getDays() {

        List<DayCode> dayCodes = dayCodeRepository.findAll();

        List<DayResponseDTO> dayResponseDTOS = dayCodes.stream()
                .map(DayResponseDTO::createDTO)
                .collect(Collectors.toList());

        return dayResponseDTOS;
    }

    @Override
    public List<GenreResponseDTO> getGenres() {

        List<Genre> genres = genreCodeRepository.findAll();

        List<GenreResponseDTO> genreResponseDTOS = genres.stream()
                .map(GenreResponseDTO::createDTO)
                .collect(Collectors.toList());

        return genreResponseDTOS;
    }

    @Override
    public List<GradeResponseDTO> getGrades() {

        List<Grade> grades = gradeCodeRepository.findAll();

        List<GradeResponseDTO> gradeResponseDTOS = grades.stream()
                .map(GradeResponseDTO::createDTO)
                .collect(Collectors.toList());

        return gradeResponseDTOS;
    }

    @Override
    public List<SortResponseDTO> getSorts() {

        List<Sort> sorts = sortCodeRepository.findAll();

        List<SortResponseDTO> sortResponseDTOS = sorts.stream()
                .map(SortResponseDTO::createDTO)
                .collect(Collectors.toList());

        return sortResponseDTOS;
    }

    @Override
    public List<StatusResponseDTO> getStatuses() {

        List<SerialStatus> statuses = statusCodeRepository.findAll();

        List<StatusResponseDTO> statusResponseDTOS = statuses.stream()
                .map(StatusResponseDTO::createDTO)
                .collect(Collectors.toList());

        return statusResponseDTOS;
    }

    @Override
    public List<WebtoonProviderResponseDTO> providers() {

        List<WebtoonProvider> webtoonProviders = webtoonProviderRepository.findAll();

        List<WebtoonProviderResponseDTO> webtoonProviderResponseDTOS = webtoonProviders.stream()
                .map(WebtoonProviderResponseDTO::createDTO)
                .collect(Collectors.toList());

        return webtoonProviderResponseDTOS;
    }

    //웹툰 검색어 top 10(네이버 쇼핑 인사이트.)
    @Override
    public List<Top10ResponseDTO> getKeywordTop10() throws JsonProcessingException {


        // TODO: 2023-04-02 아래의 http 형태를 만드는 로직을 별도의 메서드로 빼는 것이 좋아보임 - 한눈에 비즈니스 로직이 안보임.
        //요청할 url과 헤더 설정. -referer 타입이 중요함, 없으면 요청 거절당함.
        WebClient webClient = WebClient.builder()
                .baseUrl(apiKeyProperty.getKeywordRankUrl())
                .defaultHeader(HttpHeaders.USER_AGENT, apiKeyProperty.getKeywordRankHeaderUserAgent())
                .defaultHeader(HttpHeaders.REFERER, apiKeyProperty.getKeywordRankHeaderReferer())
                .build();

        //현재 날짜 구하기
        LocalDate endDate = LocalDate.now(); //현재일자
        LocalDate startDate = endDate.minusDays(7); //현재일로부터 7일 전


        //전송해야되는 데이터 담기.
        MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
        formData.add("cid",apiKeyProperty.getKeywordRankCid());
        formData.add("timeUnit",apiKeyProperty.getKeywordRankTimeUnit());
        formData.add("startDate",startDate.toString());
        formData.add("endDate",endDate.toString());
        formData.add("page","1");
        formData.add("count","10");


        //api 요청.
        String keywordRankJson = webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve() //responseEntity를 받아서 디코딩
                .bodyToMono(String.class).block();//0~1개의 객체를 받는 것 (flux는 0~N)

        //객체로 변환
        KeywordRankResponseDTO keywordRankResponseDTO = objectMapper.readValue(keywordRankJson, KeywordRankResponseDTO.class);


        //응답형태로 변경.
        return keywordRankResponseDTO.createTop10DTO();
    }
}
