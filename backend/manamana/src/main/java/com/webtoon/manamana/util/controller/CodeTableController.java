package com.webtoon.manamana.util.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    /*연재 여부 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "연재 여부 목록", description =  "연재 여부 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/status")
    public DataResponse<Object> statusList() throws Exception{

        String temp1 = "{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"status\": \"연재중\"\n" +
                "\t\t\t}";
        String temp2 = "{\n" +
                "\t\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\t\"status\": \"완결\"\n" +
                "\t\t\t}";
        String temp3 = "{\n" +
                "\t\t\t\t\t\"id\": 3,\n" +
                "\t\t\t\t\t\"status\": \"휴재\"\n" +
                "\t\t\t}";

        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);
        JSONObject jsonObj3 = (JSONObject) jsonParser.parse(temp3);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);
        jsonArray.add(jsonObj3);

        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*연령 등급 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "연령 등급 목록", description =  "연령 등급 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/grades")
    public DataResponse<Object> gradesList() throws Exception{
        String temp1 = "{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"grade\": \"전체이용가\"\n" +
                "\t\t\t}";
        String temp2 = "{\n" +
                "\t\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\t\"grade\": \"성인\"\n" +
                "\t\t\t}";

        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);

        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*연재 요일 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "연재 요일 목록", description =  "연재 요일 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/days")
    public DataResponse<Object> dayList() throws Exception{

        String temp1 = "{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"day\": \"월\"\n" +
                "\t\t\t}";
        String temp2 = "{\n" +
                "\t\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\t\"day\": \"화\"\n" +
                "\t\t\t}";
        String temp3 = "{\n" +
                "\t\t\t\t\t\"id\": 3,\n" +
                "\t\t\t\t\t\"day\": \"수\"\n" +
                "\t\t\t}";
        String temp4 = "{\n" +
                "\t\t\t\t\t\"id\": 4,\n" +
                "\t\t\t\t\t\"day\": \"목\"\n" +
                "\t\t\t}";
        String temp5 = "{\n" +
                "\t\t\t\t\t\"id\": 5,\n" +
                "\t\t\t\t\t\"day\": \"금\"\n" +
                "\t\t\t}";
        String temp6 = "{\n" +
                "\t\t\t\t\t\"id\": 6,\n" +
                "\t\t\t\t\t\"day\": \"토\"\n" +
                "\t\t\t}";
        String temp7 = "{\n" +
                "\t\t\t\t\t\"id\": 7,\n" +
                "\t\t\t\t\t\"day\": \"일\"\n" +
                "\t\t\t}";
        String temp8 = "{\n" +
                "\t\t\t\t\t\"id\": 8,\n" +
                "\t\t\t\t\t\"day\": \"기타\"\n" +
                "\t\t\t}";

        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);
        JSONObject jsonObj3 = (JSONObject) jsonParser.parse(temp3);
        JSONObject jsonObj4 = (JSONObject) jsonParser.parse(temp4);
        JSONObject jsonObj5 = (JSONObject) jsonParser.parse(temp5);
        JSONObject jsonObj6 = (JSONObject) jsonParser.parse(temp6);
        JSONObject jsonObj7 = (JSONObject) jsonParser.parse(temp7);
        JSONObject jsonObj8 = (JSONObject) jsonParser.parse(temp8);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);
        jsonArray.add(jsonObj3);
        jsonArray.add(jsonObj4);
        jsonArray.add(jsonObj5);
        jsonArray.add(jsonObj6);
        jsonArray.add(jsonObj7);
        jsonArray.add(jsonObj8);

        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*웹툰 제공자 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "웹툰 제공자 목록", description =  "웹툰 제공자 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/providers")
    public DataResponse<Object> providerList() throws Exception{

        String temp1 = "{\n" +
                "\t\t\t\"id\": 1,\n" +
                "\t\t\t\"name\": \"네이버 웹툰\",\n" +
                "\t\t\t\"providerUrl\": \"https://comic.naver.com/webtoon\"\n" +
                "\t\t}";
        String temp2 = "{\n" +
                "\t\t\t\"id\": 2,\n" +
                "\t\t\t\"name\": \"카카오 웹툰\",\n" +
                "\t\t\t\"providerUrl\": \"https://webtoon.kakao.com\"\n" +
                "\t\t}";
        String temp3 = "{\n" +
                "\t\t\t\"id\": 3,\n" +
                "\t\t\t\"name\": \"카카오 페이지\",\n" +
                "\t\t\t\"providerUrl\": \"https://page.kakao.com\"\n" +
                "\t\t}";

        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);
        JSONObject jsonObj3 = (JSONObject) jsonParser.parse(temp3);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);
        jsonArray.add(jsonObj3);

        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*정렬 조건 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "정렬 조건 목록", description =  "정렬 조건 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/sorts")
    public DataResponse<Object> sortTypeList() throws Exception{

        String temp1 = "{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"name\": \"조회순\"\n" +
                "\t\t\t}";
        String temp2 = "{\n" +
                "\t\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\t\"name\": \"별점 높은 순\"\n" +
                "\t\t\t}";
        String temp3 = "{\n" +
                "\t\t\t\t\t\"id\": 3,\n" +
                "\t\t\t\t\t\"name\": \"댓글 많은 순\"\n" +
                "\t\t\t}";

        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);
        JSONObject jsonObj3 = (JSONObject) jsonParser.parse(temp3);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);
        jsonArray.add(jsonObj3);


        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
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
    public DataResponse<Object> top10List() throws Exception{
        String temp1 = "{\n" +
                "\t\t\t\t\t\"rank\": 1,\n" +
                "\t\t\t\t\t\"keyword\": \"1초\"\n" +
                "\t\t\t}";
        String temp2 = "{\n" +
                "\t\t\t\t\t\"rank\": 2,\n" +
                "\t\t\t\t\t\"keyword\": \"투신 전생기\"\n" +
                "\t\t\t}";
        String temp3 = "{\n" +
                "\t\t\t\t\t\"rank\": 3,\n" +
                "\t\t\t\t\t\"keyword\": \"나 혼자 레벨업\"\n" +
                "\t\t\t}";
        String temp4 = "{\n" +
                "\t\t\t\t\t\"rank\": 4,\n" +
                "\t\t\t\t\t\"keyword\": \"대학원 탈출일지\"\n" +
                "\t\t\t}";
        String temp5 = "{\n" +
                "\t\t\t\t\t\"rank\": 5,\n" +
                "\t\t\t\t\t\"keyword\": \"호랑이 형님\"\n" +
                "\t\t\t}";
        String temp6 = "{\n" +
                "\t\t\t\t\t\"rank\": 6,\n" +
                "\t\t\t\t\t\"keyword\": \"검은 인간\"\n" +
                "\t\t\t}";
        String temp7 = "{\n" +
                "\t\t\t\t\t\"rank\": 7,\n" +
                "\t\t\t\t\t\"keyword\": \"행성인간\"\n" +
                "\t\t\t}";
        String temp8 = "{\n" +
                "\t\t\t\t\t\"rank\": 8,\n" +
                "\t\t\t\t\t\"keyword\": \"데드퀸\"\n" +
                "\t\t\t}";
        String temp9 = "{\n" +
                "\t\t\t\t\t\"rank\": 9,\n" +
                "\t\t\t\t\t\"keyword\": \"일렉시드\"\n" +
                "\t\t\t}";
        String temp10 = "{\n" +
                "\t\t\t\t\t\"rank\": 10,\n" +
                "\t\t\t\t\t\"keyword\": \"나혼자 만렙 뉴비\"\n" +
                "\t\t\t}";

        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);
        JSONObject jsonObj3 = (JSONObject) jsonParser.parse(temp3);
        JSONObject jsonObj4 = (JSONObject) jsonParser.parse(temp4);
        JSONObject jsonObj5 = (JSONObject) jsonParser.parse(temp5);
        JSONObject jsonObj6 = (JSONObject) jsonParser.parse(temp6);
        JSONObject jsonObj7 = (JSONObject) jsonParser.parse(temp7);
        JSONObject jsonObj8 = (JSONObject) jsonParser.parse(temp8);
        JSONObject jsonObj9 = (JSONObject) jsonParser.parse(temp9);
        JSONObject jsonObj10 = (JSONObject) jsonParser.parse(temp10);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);
        jsonArray.add(jsonObj3);
        jsonArray.add(jsonObj4);
        jsonArray.add(jsonObj5);
        jsonArray.add(jsonObj6);
        jsonArray.add(jsonObj7);
        jsonArray.add(jsonObj8);
        jsonArray.add(jsonObj9);
        jsonArray.add(jsonObj10);

        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*웹툰 장르 목록*/
    @Tag(name = "코드화 된 정보")
    @Operation(summary = "웹툰 장르 목록", description =  "웹툰 장르 목록 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/genres")public DataResponse<Object> stautsList() throws Exception{

        String temp1 = "{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"name\": \"액션\"\n" +
                "\t\t\t}";
        String temp2 = "{\n" +
                "\t\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\t\"name\": \"판타지\"\n" +
                "\t\t\t}";
        String temp3 = "{\n" +
                "\t\t\t\t\t\"id\": 3,\n" +
                "\t\t\t\t\t\"name\": \"학원\"\n" +
                "\t\t\t}";
        String temp4 = "{\n" +
                "\t\t\t\t\t\"id\": 4,\n" +
                "\t\t\t\t\t\"name\": \"액션\"\n" +
                "\t\t\t}";
        String temp5 = "{\n" +
                "\t\t\t\t\t\"id\": 5,\n" +
                "\t\t\t\t\t\"name\": \"개그\"\n" +
                "\t\t\t}";
        String temp6 = "{\n" +
                "\t\t\t\t\t\"id\": 6,\n" +
                "\t\t\t\t\t\"name\": \"무협\"\n" +
                "\t\t\t}";
        String temp7 = "{\n" +
                "\t\t\t\t\t\"id\": 7,\n" +
                "\t\t\t\t\t\"name\": \"공포 스릴러\"\n" +
                "\t\t\t}";
        String temp8 = "{\n" +
                "\t\t\t\t\t\"id\": 8,\n" +
                "\t\t\t\t\t\"name\": \"드라마\"\n" +
                "\t\t\t}";

        String temp9 = "{\n" +
                "\t\t\t\t\t\"id\": 9,\n" +
                "\t\t\t\t\t\"name\": \"로맨스\"\n" +
                "\t\t\t}";
        String temp10 = "{\n" +
                "\t\t\t\t\t\"id\": 10,\n" +
                    "\t\t\t\t\t\"name\": \"옴니버스\"\n" +
                "\t\t\t}";
        String temp11 = "{\n" +
                "\t\t\t\t\t\"id\": 11,\n" +
                "\t\t\t\t\t\"name\": \"일상\"\n" +
                "\t\t\t}";
        String temp12 = "{\n" +
                "\t\t\t\t\t\"id\": 12,\n" +
                "\t\t\t\t\t\"name\": \"BL\"\n" +
                "\t\t\t}";
        String temp13 = "{\n" +
                "\t\t\t\t\t\"id\": 13,\n" +
                "\t\t\t\t\t\"name\": \"GL\"\n" +
                "\t\t\t}";
        String temp14 = "{\n" +
                "\t\t\t\t\t\"id\": 14,\n" +
                "\t\t\t\t\t\"name\": \"SF\"\n" +
                "\t\t\t}";
        String temp15 = "{\n" +
                "\t\t\t\t\t\"id\": 15,\n" +
                "\t\t\t\t\t\"name\": \"스포츠\"\n" +
                "\t\t\t}";
        String temp16 = "{\n" +
                "\t\t\t\t\t\"id\": 16,\n" +
                "\t\t\t\t\t\"name\": \"시대극\"\n" +
                "\t\t\t}";

        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);
        JSONObject jsonObj3 = (JSONObject) jsonParser.parse(temp3);
        JSONObject jsonObj4 = (JSONObject) jsonParser.parse(temp4);
        JSONObject jsonObj5 = (JSONObject) jsonParser.parse(temp5);
        JSONObject jsonObj6 = (JSONObject) jsonParser.parse(temp6);
        JSONObject jsonObj7 = (JSONObject) jsonParser.parse(temp7);
        JSONObject jsonObj8 = (JSONObject) jsonParser.parse(temp8);
        JSONObject jsonObj9 = (JSONObject) jsonParser.parse(temp9);
        JSONObject jsonObj10 = (JSONObject) jsonParser.parse(temp10);
        JSONObject jsonObj11 = (JSONObject) jsonParser.parse(temp11);
        JSONObject jsonObj12 = (JSONObject) jsonParser.parse(temp12);
        JSONObject jsonObj13= (JSONObject) jsonParser.parse(temp13);
        JSONObject jsonObj14= (JSONObject) jsonParser.parse(temp14);
        JSONObject jsonObj15 = (JSONObject) jsonParser.parse(temp15);
        JSONObject jsonObj16 = (JSONObject) jsonParser.parse(temp16);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);
        jsonArray.add(jsonObj3);
        jsonArray.add(jsonObj4);
        jsonArray.add(jsonObj5);
        jsonArray.add(jsonObj6);
        jsonArray.add(jsonObj7);
        jsonArray.add(jsonObj8);
        jsonArray.add(jsonObj9);
        jsonArray.add(jsonObj10);
        jsonArray.add(jsonObj11);
        jsonArray.add(jsonObj12);
        jsonArray.add(jsonObj13);
        jsonArray.add(jsonObj14);
        jsonArray.add(jsonObj15);
        jsonArray.add(jsonObj16);


        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

}
