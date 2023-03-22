package com.webtoon.manamana.recommand.controller;


import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


// TODO : 더미데이터로 만든 테스트용이라 수정 필요.
/**
 * 추천 관련 API처리 컨트롤러
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/webtoons")
public class RecommandController {

    private final ResponseService responseService;

    /*추천 알고리즘을 통한 웹툰 조회*/
    @GetMapping("/recommands")
    public DataResponse<Object> recommandUserWebtoon() throws Exception{

        String temp1 = "{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"name\": \"1초\",\n" +
                "\t\t\t\t\t\"imagePath\": \"https://image-comic.pstatic.net/webtoon/725586/thumbnail/thumbnail_IMAG21_17f81846-d1a9-43fd-83a4-f9e966b6b977.jpg\",\n" +
                "\t\t\t\t\t\"authors\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\t\t\"name\": \"광운\"\n" +
                "\t\t\t\t\t\t}, ...\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t}";
        String temp2 = "{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"name\": \"투신전생기\",\n" +
                "\t\t\t\t\t\"imagePath\": \"https://image-comic.pstatic.net/webtoon/774044/thumbnail/thumbnail_IMAG21_81504afb-1a05-41b0-9650-0c9aa1d741d9.jpg\",\n" +
                "\t\t\t\t\t\"authors\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\t\t\"name\": \"청담\"\n" +
                "\t\t\t\t\t\t}, ...\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t}";


        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);

        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }


//    /*관련 웹툰 추천*/
//    @GetMapping("/{webtoon-id}/recommands")
//    public DataResponse<Object> recommandAssociationWebtoon(
//            @PathVariable("webtoon-id") long webtoonId
//    ){
//
//
//        return responseService.getDataResponse(, CustomSuccessStatus.RESPONSE_SUCCESS);
//    }
//
//    /*취향 월드컵 조회*/
//    @GetMapping("/world-cup")
//    public DataResponse<Object> worldCupWebtoon(){
//
//
//        return responseService.getDataResponse(, CustomSuccessStatus.RESPONSE_SUCCESS);
//    }
//
//    /*취향 월드컵 결과 저장.*/
//    @PostMapping("/world-cup")
//    public DataResponse<Object> worldCupWebtoonSave(
//
//    ){
//
//
//        return responseService.getDataResponse(, CustomSuccessStatus.RESPONSE_SUCCESS);
//    }
}
