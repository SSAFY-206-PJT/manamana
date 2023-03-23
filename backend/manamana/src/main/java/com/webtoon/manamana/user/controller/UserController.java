package com.webtoon.manamana.user.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.user.dto.request.UserUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 회원 관련 기능
 */
@Tag(name = "회원 관련 기능", description = "회원 관련 기능 관련 API 모음")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final ResponseService responseService;

    /*회원 정보 조회*/
    @Tag(name = "회원 관련 기능")
    @Operation(summary = "회원 정보 조회", description =  "회원 정보 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/{user-id}")
    public DataResponse<Object> findUser(
            @PathVariable("user-id") long userId) throws Exception{

        String temp = "{\n" +
                "\t\t\"id\" : 1, \n" +
                "\t\t\"email\" : \"test@gmail.com\",\n" +
                "\t\t\"nickname\" : \"test\", \n" +
                "\t\t\"imagePath\" : \"/test/test.jpg\",\n" +
                "\t\t\"gender\" : \"남\", \n" +
                "\t\t\"age\" : 27,\n" +
                "\t\t\"likeCount\" : 0,\n" +
                "\t\t\"scoreCount\" : 3 \n" +
                "\t}";

        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj = (JSONObject) jsonParser.parse(temp);


        return responseService.getDataResponse(jsonObj, CustomSuccessStatus.RESPONSE_SUCCESS);
    }


    /*회원 정보 수정*/
    @Tag(name = "회원 관련 기능")
    @Operation(summary = "회원 정보 수정", description =  "회원 정보 수정 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @PatchMapping(value="/{user-id}", consumes = {"multipart/form-data"})
    public CommonResponse updateUser(
            @PathVariable("user-id") long userId,
            @RequestPart("data") UserUpdateRequestDTO userUpdateRequestDTO,
            @RequestPart(value = "userImg",required = false) MultipartFile multipartFile){

        return responseService.getSuccessResponse();
    }
//
    /*회원 탈퇴*/
    @Tag(name = "회원 관련 기능")
    @Operation(summary = "회원 탈퇴", description =  "회원 탈퇴 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @DeleteMapping("/{user-id}")
    public CommonResponse deleteUser(
            @PathVariable("user-id") long userId){


        return responseService.getSuccessResponse();
    }




}
