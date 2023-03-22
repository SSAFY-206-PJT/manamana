package com.webtoon.manamana.webtoon.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 작품에 대한 추가 기능들 - 댓글 제외
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/webtoons")
public class WebtoonAdditionController {

    private final ResponseService responseService;

    /*댓글 신고*/
    @PatchMapping("/{webtoon-id}/comments/{comment-id}/report")
    public CommonResponse reportComment(
            @PathVariable("webtoon-id") long webtoonId,
            @PathVariable("comment-id") long commentId
    ){

        log.info("[신고 확인] - webtoon-id : {} , comment-id : {}", webtoonId,commentId);
        return responseService.getSuccessResponse();
    }

    /*작품 관심등록 */
    @PatchMapping("/{webtoon-id}/like")
    public CommonResponse likeComment(
            @PathVariable("webtoon-id") long webtoonId
    ){

        log.info("[관심등록 확인] - webtoon-id : {} ", webtoonId);

        return responseService.getSuccessResponse();
    }

    /*댓글 워드 클라우드*/
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
    @GetMapping("/{webtoon-id}/scores")
    public DataResponse<Object> scoreWebtoon(
            @PathVariable("webtoon-id") long webtoonId
    ) throws Exception{
        log.info("[개인이 평가한 평점 확인] - webtoon-id : {} ", webtoonId);

        String temp = "{\n" +
                "\t\t\t\t\"score\": 5\n" +
                "\t\t}";
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj = (JSONObject) jsonParser.parse(temp);

        return responseService.getDataResponse(jsonObj,CustomSuccessStatus.RESPONSE_SUCCESS);

    }

    /*작품 평점 생성*/
    @PostMapping("/{webtoon-id}/scores")
    public CommonResponse createWebtoonScore(
            @PathVariable("webtoon-id") long webtoonId,
            @RequestBody Map<String,Integer> requestMap){


        log.info("[작품 평점 생성 확인] - webtoon-id : {}, score : {}", webtoonId, requestMap.get("score"));

        return responseService.getSuccessResponse();
    }


    /*작품 평점 수정.*/
    @PatchMapping("/{webtoon-id}/scores")
    public CommonResponse updateWebtoonScore(
            @PathVariable("webtoon-id") long webtoonId,
            @RequestBody Map<String,Integer> requestMap){

        log.info("[작품 평점 수정 확인] - webtoon-id : {}, score : {}", webtoonId, requestMap.get("score"));


        return responseService.getSuccessResponse();
    }
}
