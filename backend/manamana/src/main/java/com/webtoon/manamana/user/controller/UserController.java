package com.webtoon.manamana.user.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.auth.DTO.UserPrincipal;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.user.dto.request.UserUpdateRequestDTO;
import com.webtoon.manamana.user.dto.response.UserResponseDTO;
import com.webtoon.manamana.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final UserService userService;
    private final ResponseService responseService;

    /*회원 정보 조회*/
    @Tag(name = "회원 관련 기능")
    @Operation(summary = "회원 정보 조회", description =  "회원 정보 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/{user-id}")
    public DataResponse<UserResponseDTO> findUser(
            @PathVariable(name="user-id", required = false) long userId,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        //TODO : pathvariable의 id랑 jwt의 id랑 비교처리 필요.
        long authUserId = userPrincipal.getId();

        UserResponseDTO userResponseDTO = userService.getUser(authUserId);

        return responseService.getDataResponse(userResponseDTO, CustomSuccessStatus.RESPONSE_SUCCESS);
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
            @RequestPart(value = "userImg",required = false) MultipartFile multipartFile,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        //TODO : pathvariable의 id랑 jwt의 id랑 비교처리 필요.
        long authUserId = userPrincipal.getId();

        userService.updateUser(authUserId, userUpdateRequestDTO, multipartFile);

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
            @PathVariable("user-id") long userId,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        // TODO : 인증토큰의 ID와 PathVariable의 ID가 같은지 확인.
        long authUserId = userPrincipal.getId();

        userService.removeUser(authUserId);

        return responseService.getSuccessResponse();
    }




}
