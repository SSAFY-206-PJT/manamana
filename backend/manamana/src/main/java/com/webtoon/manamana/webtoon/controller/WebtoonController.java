package com.webtoon.manamana.webtoon.controller;

import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonDetailDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonListDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonProviderDTO;
import com.webtoon.manamana.webtoon.service.common.WebtoonService;
import com.webtoon.manamana.webtoon.util.WebtoonListFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    private final WebtoonService webtoonService;


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
            Pageable pageable){

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

        List<WebtoonListDTO> webtoonListDTOS = webtoonService.findWebtoonAll(filter, pageable);

        if(webtoonListDTOS.isEmpty()) return responseService.getDataResponse(webtoonListDTOS,CustomSuccessStatus.RESPONSE_NO_CONTENT);

        return responseService.getDataResponse(webtoonListDTOS, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    @Tag(name = "웹툰 정보")
    @Operation(summary = "웹툰 상세 조회", description =  "웹툰 상세 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러")
    })
    @GetMapping("/{webtoon-id}")
    public DataResponse<WebtoonDetailDTO> webtoonDetail(
            @PathVariable("webtoon-id") long webtoonId){

        long authUserId = 1L;

        WebtoonDetailDTO webtoonOne = webtoonService.findWebtoonOne(authUserId, webtoonId);

        return responseService.getDataResponse(webtoonOne, CustomSuccessStatus.RESPONSE_SUCCESS);

    }

    /*웹툰 작품 플랫폼 정보*/
    @Tag(name = "웹툰 정보")
    @Operation(summary = "웹툰 플랫폼 정보", description =  "웹툰 플랫폼 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러")
    })
    @GetMapping("/{webtoon-id}/providers")
    public DataResponse<Object> getWebtoonProvider(
            @PathVariable("webtoon-id") long webtoonId){


        WebtoonProviderDTO webtoonProviderDTO = webtoonService.findWebtoonProviderAll(webtoonId);

        return responseService.getDataResponse(webtoonProviderDTO,CustomSuccessStatus.RESPONSE_SUCCESS);
    }
}
