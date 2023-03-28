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
    public DataResponse<List<RecommandWebtoonResponseDTO>> recommandUserWebtoon() throws Exception{

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
            @PathVariable("webtoon-id") long webtoonId) throws Exception{

        // TODO : webtoonId PathVariable 사용하는 부분

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
    public DataResponse<Object> worldCupWebtoon() throws Exception{

        String temp1 = "{\n" +
                "\t\t\t\"id\": 1, \n" +
                "\t\t\t\"imagePath\": \"https://image-comic.pstatic.net/webtoon/774044/thumbnail/thumbnail_IMAG21_81504afb-1a05-41b0-9650-0c9aa1d741d9.jpg\"\n" +
                "\t\t}";
        String temp2 = "{\n" +
                "\t\t\t\"id\": 2, \n" +
                "\t\t\t\"imagePath\": \"https://image-comic.pstatic.net/webtoon/805691/thumbnail/thumbnail_IMAG21_8b092acf-e28c-47d2-9b5f-3a02df85103f.jpg\"\n" +
                "\t\t}";
        String temp3 = "{\n" +
                "\t\t\t\"id\": 3, \n" +
                "\t\t\t\"imagePath\": \"https://image-comic.pstatic.net/webtoon/795529/thumbnail/thumbnail_IMAG21_3689631394330325350.jpg\"\n" +
                "\t\t}";
        String temp4 = "{\n" +
                "\t\t\t\"id\": 4, \n" +
                "\t\t\t\"imagePath\": \"https://image-comic.pstatic.net/webtoon/736277/thumbnail/thumbnail_IMAG21_bbbe3f76-021e-4dbc-830a-4358c1abec0c.jpg\"\n" +
                "\t\t}";
        String temp5 = "{\n" +
                "\t\t\t\"id\": 5, \n" +
                "\t\t\t\"imagePath\": \"https://image-comic.pstatic.net/webtoon/759567/thumbnail/thumbnail_IMAG21_3991657355780766050.jpg\"\n" +
                "\t\t}";
        String temp6 = "{\n" +
                "\t\t\t\"id\": 6, \n" +
                "\t\t\t\"imagePath\": \"https://image-comic.pstatic.net/webtoon/695796/thumbnail/thumbnail_IMAG21_332bb25b-c77d-477f-9979-5a8607ebd7a5.jpg\"\n" +
                "\t\t}";

        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);
        JSONObject jsonObj3 = (JSONObject) jsonParser.parse(temp3);
        JSONObject jsonObj4 = (JSONObject) jsonParser.parse(temp4);
        JSONObject jsonObj5 = (JSONObject) jsonParser.parse(temp5);
        JSONObject jsonObj6 = (JSONObject) jsonParser.parse(temp6);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);
        jsonArray.add(jsonObj3);
        jsonArray.add(jsonObj4);
        jsonArray.add(jsonObj5);
        jsonArray.add(jsonObj6);

        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
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
            @RequestBody WorldCupRequestDTO worldCupRequestDTO) throws Exception{

        String temp = " {\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"name\": \"호랑이형님\",\n" +
                "\t\t\"imagePath\": \"https://image-comic.pstatic.net/webtoon/650305/thumbnail/thumbnail_IMAG21_3631086797392995425.jpg\"\n" +
                "\t}";

        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj = (JSONObject) jsonParser.parse(temp);

        log.info(worldCupRequestDTO.getId().toString());
        return responseService.getDataResponse(jsonObj,CustomSuccessStatus.RESPONSE_SUCCESS);
    }
}
