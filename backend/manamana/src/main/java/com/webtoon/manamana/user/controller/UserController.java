package com.webtoon.manamana.user.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.user.dto.reponse.UserUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 회원 관련 기능
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final ResponseService responseService;

    /*회원 정보 조회*/
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
    @PatchMapping(value="/{user-id}", consumes = {"multipart/form-data"})
    public CommonResponse updateUser(
            @PathVariable("user-id") long userId,
            @RequestPart("data") UserUpdateRequestDTO userUpdateRequestDTO,
            @RequestPart(value = "userImg",required = false) MultipartFile multipartFile){

        return responseService.getSuccessResponse();
    }
//
    /*회원 탈퇴*/
    @DeleteMapping("/{user-id}")
    public CommonResponse deleteUser(
            @PathVariable("user-id") long userId){


        return responseService.getSuccessResponse();
    }




}
