package com.webtoon.manamana.webtoon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.webtoon.dto.response.*;
import com.webtoon.manamana.webtoon.util.WebtoonListFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 웹툰 정보
 */

@Tag(name = "웹툰 정보", description = "웹툰 정보관련 API 모음")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/webtoons")
public class WebtoonController {

    private final ResponseService responseService;


    // TODO : 아래처럼 쿼리 스트링을 하나씩 받는게 아니라 맵으로 받던가 해야됨 - Pageable 사용하려고 하다보니까 아래와 같은 형식이 됨.
    //전체 조회.
    @Tag(name = "웹툰 정보")
    @Operation(summary = "웹툰 전체 조회", description =  "모든 웹툰 조회 - 쿼리 스트링으로 검색가능, 페이징 처리 가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping
    public DataResponse<List<WebtoonListDTO>> webtoonList(
            @Parameter(description = "키워드", required = false, example = "박태준") @RequestParam(required = false) String keyword,
            @Parameter(description = "연재 여부", required = false, example = "1") @RequestParam(required = false) Integer statusId,
            @Parameter(description = "연령등급", required = false, example = "1") @RequestParam(required = false) Integer gradeId,
            @Parameter(description = "장르", required = false, example = "1") @RequestParam(required = false) Integer genreId,
            @Parameter(description = "요일", required = false, example = "1") @RequestParam(required = false) Integer dayId,
            @Parameter(description = "정렬조건", required = false, example = "1") @RequestParam(required = false) Integer sortType,
            Pageable pageable
    ){

        log.info("page = {}, size = {}",pageable.getOffset(),pageable.getPageSize());
        //쿼리 스트링을 map으로 받아서 객체에 매핑함.
        WebtoonListFilter filter = WebtoonListFilter.builder()
                .keyword(keyword)
                .statusId(statusId)
                .genreId(genreId)
                .gradeId(gradeId)
                .dayId(dayId)
                .sortType(sortType).build();

        //TODO : 더미데이터로 리턴하기 때문에 실제 로직으로 바꿔야 됨.

        List<WebtoonListDTO> returnArray = new ArrayList<>();
        List<AuthorDTO> authorArray1 = new ArrayList<>();
        AuthorDTO authorDTO1 = AuthorDTO.builder()
                .id(1)
                .name("시니")
                .build();
        AuthorDTO authorDTO2 = AuthorDTO.builder()
                .id(2)
                .name("광운")
                .build();
        authorArray1.add(authorDTO1);
        authorArray1.add(authorDTO2);

        WebtoonListDTO webtoonListDTO = WebtoonListDTO.builder()
                .id(1)
                .name("1초")
                .status("연재중")
                .imagePath("https://image-comic.pstatic.net/webtoon/725586/thumbnail/thumbnail_IMAG21_17f81846-d1a9-43fd-83a4-f9e966b6b977.jpg")
                .authors(authorArray1)
                .build();

        List<AuthorDTO> authorArray2 = new ArrayList<>();
        AuthorDTO authorDTO3 = AuthorDTO.builder()
                .id(3)
                .name("박태준만화회사")
                .build();
        AuthorDTO authorDTO4 = AuthorDTO.builder()
                .id(4)
                .name("정종택")
                .build();
        authorArray2.add(authorDTO3);
        authorArray2.add(authorDTO4);

        WebtoonListDTO webtoonListDTO2 = WebtoonListDTO.builder()
                .id(2)
                .name("김부장")
                .status("연재중")
                .imagePath("https://image-comic.pstatic.net/webtoon/783053/thumbnail/thumbnail_IMAG21_d7732f14-26da-4e35-8762-660cc87b53f1.jpg")
                .authors(authorArray2)
                .build();

        returnArray.add(webtoonListDTO);
        returnArray.add(webtoonListDTO2);
        return responseService.getDataResponse(returnArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    @Tag(name = "웹툰 정보")
    @Operation(summary = "웹툰 상세 조회", description =  "웹툰 상세 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러")
    })
    @GetMapping("/{webtoon-id}")
    public DataResponse<WebtoonDetailDTO> webtoonDetail(
            @PathVariable("webtoon-id") long webtoonId
    ){

        List<AuthorDTO> authorDTOList = new ArrayList<>();
        AuthorDTO authorDTO1 = AuthorDTO.builder()
                .id(1)
                .name("시니")
                .build();
        AuthorDTO authorDTO2 = AuthorDTO.builder()
                .id(2)
                .name("광운")
                .build();
        authorDTOList.add(authorDTO1);
        authorDTOList.add(authorDTO2);

        List<GenreDTO> genreDTOList = new ArrayList<>();
        GenreDTO genreDTO1 = GenreDTO.builder()
                .id(1)
                .name("드라마")
                .build();
        GenreDTO genreDTO2 = GenreDTO.builder()
                .id(2)
                .name("직업 드라마")
                .build();
        genreDTOList.add(genreDTO1);
        genreDTOList.add(genreDTO2);

        List<DayDTO> dayDTOList = new ArrayList<>();
        DayDTO dayDTO1 = DayDTO.builder()
                .id(1)
                .codeId(1)
                .build();
        DayDTO dayDTO2 = DayDTO.builder()
                .id(2)
                .codeId(2)
                .build();
        dayDTOList.add(dayDTO1);
        dayDTOList.add(dayDTO2);

        AdditionDTO additionDTO = AdditionDTO.builder()
                .id(1)
                .view(10)
                .scoreCount(100)
                .scoreAverage(5)
                .build();

        WebtoonDetailDTO webtoonDetailDTO = WebtoonDetailDTO.builder()
                .id(1)
                .name("1초")
                .imagePath("https://image-comic.pstatic.net/webtoon/725586/thumbnail/thumbnail_IMAG21_17f81846-d1a9-43fd-83a4-f9e966b6b977.jpg")
                .plot("소방관 이야기 입니다\n근데 초능력을 곁들인")
                .grade("전체이용가")
                .status("연재중")
                .webtoonUrl("https://comic.naver.com/webtoon/list?titleId=725586")
                .webtoonId("725586")
                .startDate("2023.03.13")
                .totalEpisode(100)
                .colorHsl("385,20,25")
                .authors(authorDTOList)
                .genres(genreDTOList)
                .days(dayDTOList)
                .additions(additionDTO)
                .build();

        return responseService.getDataResponse(webtoonDetailDTO, CustomSuccessStatus.RESPONSE_SUCCESS);

    }





}
