package com.webtoon.manamana.util.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.config.ApiKeyProperty;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.util.dto.response.OpenApiKeyword;
import com.webtoon.manamana.util.dto.response.StatusResponseDTO;
import com.webtoon.manamana.util.service.CodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *코드화 시켜둔 목록 조회.
 * */
@Tag(name = "코드화 된 정보", description = "코드화 테이블 정보 API 모음")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/webtoons/list")
public class CodeTableController {

    private final ResponseService responseService;
    private final CodeService codeService;


    /*연재 여부 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "연재 여부 목록", description =  "연재 여부 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/status")
    public DataResponse<Object> statusList(){

        return responseService.getDataResponse(codeService.getStatuses(), CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*연령 등급 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "연령 등급 목록", description =  "연령 등급 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/grades")
    public DataResponse<Object> gradesList(){

        return responseService.getDataResponse(codeService.getGrades(), CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*연재 요일 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "연재 요일 목록", description =  "연재 요일 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/days")
    public DataResponse<Object> dayList(){

        return responseService.getDataResponse(codeService.getDays(), CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*웹툰 제공자 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "웹툰 제공자 목록", description =  "웹툰 제공자 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/providers")
    public DataResponse<Object> providerList(){

        return responseService.getDataResponse(codeService.providers(), CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*정렬 조건 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "정렬 조건 목록", description =  "정렬 조건 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/sorts")
    public DataResponse<Object> sortTypeList(){

        return responseService.getDataResponse(codeService.getSorts(), CustomSuccessStatus.RESPONSE_SUCCESS);
    }
    // TODO : 코드화 테이블 정보는 아니기 때문에 다른 디렉토리로 옮기는 것 생각 해야 됨.
    /*웹툰 키워드 top10*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "웹툰 키워드 TOP10", description =  "웹툰 키워드 TOP10 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/keywords")
    public DataResponse<Object> top10List() throws JsonProcessingException {

        return responseService.getDataResponse(codeService.getKeywordTop10(), CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*웹툰 장르 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "웹툰 장르 목록", description =  "웹툰 장르 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/genres")
    public DataResponse<Object> genreList(){


        return responseService.getDataResponse(codeService.getGenres(), CustomSuccessStatus.RESPONSE_SUCCESS);
    }



}
