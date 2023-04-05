package com.webtoon.manamana.webtoon.controller;

import com.webtoon.manamana.auth.DTO.UserPrincipal;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonListTotalDTO;
import com.webtoon.manamana.webtoon.util.WebtoonFilterDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonDetailDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonListDTO;
import com.webtoon.manamana.webtoon.dto.response.common.WebtoonProviderDTO;
import com.webtoon.manamana.webtoon.service.common.WebtoonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PostMapping
    public DataResponse<WebtoonListTotalDTO> webtoonList(
            @Parameter(description = "키워드", required = false, example = "박태준") @RequestParam(required = false) String keyword,
            @Parameter(description = "정렬조건", required = false, example = "1") @RequestParam(required = false) Integer sortType,
            @RequestBody(required = false) WebtoonFilterDTO webtoonFilterDTO,
            Pageable pageable){

        log.info("page = {}, size = {}",pageable.getOffset(),pageable.getPageSize());

        //body의 검색조건이 null이면
        if(webtoonFilterDTO == null) webtoonFilterDTO = new WebtoonFilterDTO();

        //필터객체에 값넣기.
        webtoonFilterDTO.setKeyword(keyword);
        webtoonFilterDTO.setSortType(sortType);


        WebtoonListTotalDTO webtoonListTotalDTO = webtoonService.findWebtoonAll(webtoonFilterDTO, pageable);


        if(webtoonListTotalDTO.getCount() == 0) return responseService.getDataResponse(webtoonListTotalDTO,CustomSuccessStatus.RESPONSE_NO_CONTENT);

        return responseService.getDataResponse(webtoonListTotalDTO, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    @Tag(name = "웹툰 정보")
    @Operation(summary = "웹툰 상세 조회", description =  "웹툰 상세 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러")
    })
    @GetMapping("/{webtoon-id}")
    public DataResponse<WebtoonDetailDTO> webtoonDetail(
            @PathVariable("webtoon-id") long webtoonId,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        long authUserId = userPrincipal.getId();


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

    /*웹툰 보러가기 누를때 가중치 증가*/
    @Tag(name = "웹툰 정보")
    @Operation(summary = "웹툰 보러가기 가중치 증가", description =  "웹툰 보러가기 가중치 증가")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러")
    })
    @GetMapping("/{webtoon-id}/redirect/scores")
    public CommonResponse moveToWebtoonWeight(
            @PathVariable("webtoon-id") long webtoonId,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        long authUserId = userPrincipal.getId();


        webtoonService.upToWeightWebtoon(authUserId,webtoonId);

        return responseService.getSuccessResponse();
    }
}
