package com.webtoon.manamana.recommand.controller;


import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.recommand.dto.request.WorldCupRequestDTO;
import com.webtoon.manamana.recommand.dto.response.ApiResponseDTO;
import com.webtoon.manamana.recommand.dto.response.RecommandWebtoonResponseDTO;
import com.webtoon.manamana.recommand.dto.response.WorldCupResponseDTO;
import com.webtoon.manamana.recommand.dto.response.WorldCupResultDTO;
import com.webtoon.manamana.recommand.service.RecommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// TODO : 더미데이터로 만든 테스트용이라 수정 필요.
/**
 * 추천 관련 API처리 컨트롤러
 */
@Tag(name = "추천 관련 기능", description = "웹툰 추천 관련 기능 관련 API 모음")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/webtoons")
public class RecommandController {

    private final RecommandService recommandService;
    private final ResponseService responseService;

    /*추천 알고리즘을 통한 웹툰 조회*/
    @Tag(name = "추천 관련 기능")
    @Operation(summary = "사용자 별 웹툰 추천 기능", description =  "사용자에 맞춘 웹툰 추천 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/recommands")
    public DataResponse<List<RecommandWebtoonResponseDTO>> recommandUserWebtoon() throws Exception {

        List<RecommandWebtoonResponseDTO> recommandWebtoonResponseDTOS = recommandService.recommandUserWebtoon();

        return responseService.getDataResponse(recommandWebtoonResponseDTOS, CustomSuccessStatus.RESPONSE_SUCCESS);
    }


    /*관련 웹툰 추천*/
    @Tag(name = "추천 관련 기능")
    @Operation(summary = "관련 웹툰 추천", description =  "관련 웹툰 추천 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/{webtoon-id}/recommands")
    public DataResponse< List<RecommandWebtoonResponseDTO>> recommandAssociationWebtoon(
            @PathVariable("webtoon-id") long webtoonId) throws Exception {

        // TODO : webtoonId PathVariable 어디에 사용?

        List<RecommandWebtoonResponseDTO> recommandWebtoonResponseDTOS = recommandService.recommandAssociationWebtoon();

        return responseService.getDataResponse(recommandWebtoonResponseDTOS, CustomSuccessStatus.RESPONSE_SUCCESS);
    }


    /*취향 월드컵 조회*/
    @Tag(name = "추천 관련 기능")
    @Operation(summary = "취향 월드컵 조회", description =  "취향 월드컵에 사용할 웹툰 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/world-cup")
    public DataResponse<Object> worldCupWebtoon() throws Exception {

        List<WorldCupResponseDTO> worldCupResponseDTOS = recommandService.worldCupWebtoonSearch();

        return responseService.getDataResponse(worldCupResponseDTOS, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*취향 월드컵 결과 저장.*/
    @Tag(name = "추천 관련 기능")
    @Operation(summary = "취향 월드컵 결과 저장", description =  "취향 월드컵에서 나온 결과 저장 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @PostMapping("/world-cup")
    public DataResponse<Object> worldCupWebtoonSave(
            @RequestBody WorldCupRequestDTO worldCupRequestDTO) throws Exception {

        WorldCupResultDTO worldCupResultDTO = recommandService.worldCupWebtoonSave(worldCupRequestDTO);

        return responseService.getDataResponse(worldCupResultDTO, CustomSuccessStatus.RESPONSE_SUCCESS);
    }
}
