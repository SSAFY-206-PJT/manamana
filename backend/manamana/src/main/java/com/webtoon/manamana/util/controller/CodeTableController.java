package com.webtoon.manamana.util.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *코드화 시켜둔 목록 조회.
 * */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/webtoons/list")
public class CodeTableController {

    private final ResponseService responseService;


    /*연재 여부 목록*/
    @GetMapping("/status")
    public DataResponse<Object> stautsList() throws Exception{

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

//    /*정렬 조건 목록*/
//    @GetMapping("/sorts")
//    public DataResponse<Object> stautsList() throws Exception{
//
//
//        return responseService.getDataResponse(, CustomSuccessStatus.RESPONSE_SUCCESS);
//    }
//    /*웹툰 키워드 top10*/
//    @GetMapping("keywords")
//    public DataResponse<Object> stautsList() throws Exception{
//
//
//        return responseService.getDataResponse(, CustomSuccessStatus.RESPONSE_SUCCESS);
//    }
//
//    /*웹툰 장르 목록*/
//    @GetMapping("/genres")public DataResponse<Object> stautsList() throws Exception{
//
//        return responseService.getDataResponse(, CustomSuccessStatus.RESPONSE_SUCCESS);
//    }

}
