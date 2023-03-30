package com.webtoon.manamana.recommand.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.UserWebtoon;
import com.webtoon.manamana.entity.webtoon.Author;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.entity.webtoon.WebtoonGenre;
import com.webtoon.manamana.recommand.dto.request.ApiAuthorDTO;
import com.webtoon.manamana.recommand.dto.request.AssosiationApiRequestDTO;
import com.webtoon.manamana.recommand.dto.request.RecommandWebtoonRequestDTO;
import com.webtoon.manamana.recommand.dto.request.WorldCupRequestDTO;
import com.webtoon.manamana.recommand.dto.response.*;
import com.webtoon.manamana.user.repository.user.UserWebtoonRepository;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonGenreRepository;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonGenreRepositorySupport;
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
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommandServiceImpl implements RecommandService {

    private final UserWebtoonRepository userWebtoonRepository;
    private final WebtoonRepository webtoonRepository;
    private final WebtoonRepositorySupport webtoonRepositorySupport;
    private final WebtoonGenreRepository webtoonGenreRepository;
    private final WebtoonGenreRepositorySupport webtoonGenreRepositorySupport;

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

        List<RecommandWebtoonResponseDTO> recommandWebtoonResponseDTOS = objectMapper.readValue(response.getBody(), ApiResponseDTO.class).getResult();

        return recommandWebtoonResponseDTOS;
    }

    /* 관련 웹툰 추천 */
    @Override
    public  List<RecommandWebtoonResponseDTO> recommandAssociationWebtoon(long webtoonId) throws Exception {

        /*
            TODO : Exception 던졌던거 처리
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

        HashMap<Long, List<AssosiationApiRequestDTO>> map = new HashMap<>();
        map.put(webtoonId, assosiationApiRequestDTOS);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(map);

        HttpEntity entity = new HttpEntity(request, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange("http://127.0.0.1:8000/assosiation", HttpMethod.POST, entity, String.class);

        List<AssosiationWebtoonResponseDTO> assosiationWebtoonResponseDTOS = objectMapper.readValue(response.getBody(), AssosiationApiResponseDTO.class).getResult();
        List<RecommandWebtoonResponseDTO> recommandWebtoonResponseDTOS = new ArrayList<>();

        for (AssosiationWebtoonResponseDTO assosiationWebtoonResponseDTO : assosiationWebtoonResponseDTOS) {

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
        }

        return recommandWebtoonResponseDTOS;
    }

    /* 취향 월드컵 조회 */
    @Override
    public List<WorldCupResponseDTO> worldCupWebtoonSearch() {

        Set<Long> worldCupWebtoon = new HashSet<>();

        for (int i = 1; i <= 16; i++) {
            List<Long> genreTop10 = webtoonGenreRepositorySupport.findGenreWebtoonTOP10(i);

            if (genreTop10.size() > 2) {

                Random random = new Random();
                List<Long> randomTwo = genreTop10.stream()
                        .filter(e -> random.nextBoolean())
                        .limit(2)
                        .collect(Collectors.toList());

                worldCupWebtoon.add(randomTwo.get(0));
                worldCupWebtoon.add(randomTwo.get(1));
            }
        }

        List<WorldCupResponseDTO> worldCupResponseDTOS = new ArrayList<>();

        for (Long webtoonId : worldCupWebtoon) {
            Webtoon webtoon = webtoonRepositorySupport.findWebtoonOne(webtoonId)
                    .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_WEBTOON));

            worldCupResponseDTOS.add(
                    WorldCupResponseDTO.builder()
                            .id(webtoon.getId())
                            .imagePath(webtoon.getImagePath())
                            .build()
            );
        }

        return worldCupResponseDTOS;
    }

    /* 취향 월드컵 결과 저장 */
    @Override
    @Transactional
    public WorldCupResultDTO worldCupWebtoonSave(WorldCupRequestDTO worldCupRequestDTO) {

        for (Long webtoonId : worldCupRequestDTO.getId()) {

            List<WebtoonGenre> webtoonGenres = webtoonGenreRepository.findByWebtoonId(webtoonId);

            //  선택된 웹툰의 장르들 (중복X)
            Set<Integer> selectedGenreId = new HashSet<>();

            for (WebtoonGenre genre : webtoonGenres) {
                selectedGenreId.add(genre.getGenre().getId());
            }
        }

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
}
