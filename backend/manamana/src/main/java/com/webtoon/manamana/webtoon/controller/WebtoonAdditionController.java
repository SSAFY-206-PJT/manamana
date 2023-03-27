package com.webtoon.manamana.webtoon.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.webtoon.dto.request.ScoreRequestDTO;
import com.webtoon.manamana.webtoon.dto.response.addition.ScoreResponseDTO;
import com.webtoon.manamana.webtoon.service.addition.WebtoonAdditionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 작품에 대한 추가 기능들 - 댓글 제외
 */
@Tag(name = "웹툰 추가 기능", description = "웹툰 추가 기능 관련 API 모음")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/webtoons")
public class WebtoonAdditionController {

    private final ResponseService responseService;
    private final WebtoonAdditionServiceImpl webtoonAdditionService;
    

    /*댓글 신고*/
    @Tag(name = "웹툰 추가 기능")
    @Operation(summary = "웹툰 댓글 신고", description =  "웹툰 댓글 신고 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @PatchMapping("/{webtoon-id}/comments/{comment-id}/report")
    public CommonResponse reportComment(
            @PathVariable("webtoon-id") long webtoonId,
            @PathVariable("comment-id") long commentId){

        long authUserId = 1L;

        webtoonAdditionService.commentReport(authUserId,webtoonId,commentId);

        return responseService.getSuccessResponse();
    }

    /*작품 관심등록 */
    @Tag(name = "웹툰 추가 기능")
    @Operation(summary = "관심 웹툰 등록", description =  "관심 웹툰 등록 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @PatchMapping("/{webtoon-id}/like")
    public CommonResponse likeComment(
            @PathVariable("webtoon-id") long webtoonId){

        long authUserId = 1L;
        log.info("[관심등록 확인] - webtoon-id : {} ", webtoonId);
        webtoonAdditionService.createLikeWebtoon(authUserId,webtoonId);

        return responseService.getSuccessResponse();
    }

    /*댓글 워드 클라우드*/
    //TODO : 기능 보류
    @Tag(name = "웹툰 추가 기능")
    @Operation(summary = "댓글 워드 클라우드", description =  "댓글 워드 클라우드 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/{webtoon-id}/word-cloud")
    public DataResponse<Object> wordCloudComment(
            @PathVariable("webtoon-id") long webtoonId
    )throws Exception{


        String temp1 = "{\n" +
                "\t\t\t\t\"context\": \"재미\",\n" +
                "\t\t\t\t\"rank\": 1,\n" +
                "\t\t\t}";
        String temp2 = "{\n" +
                "\t\t\t\t\"context\": \"양산\",\n" +
                "\t\t\t\t\"rank\": 2,\n" +
                "\t\t\t}";

        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);
        log.info("[워드 클라우드 확인] - webtoon-id : {} ", webtoonId);
        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*개인이 평가한 평점*/
    @Tag(name = "웹툰 추가 기능")
    @Operation(summary = "개인 평점 조회", description =  "개인 평점 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/{webtoon-id}/scores")
    public DataResponse<Object> scoreWebtoon(
            @PathVariable("webtoon-id") long webtoonId
    ) throws Exception{
        log.info("[개인이 평가한 평점 확인] - webtoon-id : {} ", webtoonId);
        
        long authUserId = 1L;

        ScoreResponseDTO webtoonUserScore = webtoonAdditionService.getWebtoonUserScore(authUserId, webtoonId);

        return responseService.getDataResponse(webtoonUserScore,CustomSuccessStatus.RESPONSE_SUCCESS);

    }

    /*작품 평점 생성*/
    @Tag(name = "웹툰 추가 기능")
    @Operation(summary = "개인 평점 등록", description =  "개인 평점 등록 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @PostMapping("/{webtoon-id}/scores")
    public CommonResponse createWebtoonScore(
            @PathVariable("webtoon-id") long webtoonId,
            @RequestBody ScoreRequestDTO scoreRequestDTO){

        long authUserId = 1L;
        log.info("[작품 평점 생성 확인] - webtoon-id : {}, score : {}", webtoonId, scoreRequestDTO.getScore());

        webtoonAdditionService.createWebtoonUserScore(authUserId,webtoonId,scoreRequestDTO.getScore());

        return responseService.getSuccessResponse();
    }

}
