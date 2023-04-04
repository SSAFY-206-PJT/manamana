package com.webtoon.manamana.user.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.auth.DTO.UserPrincipal;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.user.dto.request.GenreRequestDTO;
import com.webtoon.manamana.user.dto.request.IdLongMultiSelectRequestDTO;
import com.webtoon.manamana.user.dto.request.WebtoonRequestDTO;
import com.webtoon.manamana.user.dto.response.GenreResponseDTO;
import com.webtoon.manamana.user.dto.response.UserCommentResponseDTO;
import com.webtoon.manamana.user.dto.response.WebtoonInfoDTO;
import com.webtoon.manamana.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "회원 상호작용 기능", description = "회원 상호작용 관련 기능 관련 API 모음")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserInteractionController {

    private final UserService userService;
    private final ResponseService responseService;

    /*회원이 작성한 댓글 조회*/
    @Tag(name = "회원 상호작용 기능")
    @Operation(summary = "작성한 댓글 조회", description =  "작성한 댓글 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/{user-id}/comments")
    public DataResponse<Object> findUserComment(
            @PathVariable("user-id") long userId,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        // TODO : OAuth 완성후 userId 수정필요
        long authUserId = userPrincipal.getId();

        List<UserCommentResponseDTO> commentResponseDTOS = userService.getUserCommentAll(authUserId);

        //데이터가 없으면 No Content
        if(commentResponseDTOS.isEmpty()){
            return responseService.getDataResponse(commentResponseDTOS,CustomSuccessStatus.RESPONSE_NO_CONTENT);
        }

        return responseService.getDataResponse(commentResponseDTOS,CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*관심 웹툰 조회*/
    @Tag(name = "회원 상호작용 기능")
    @Operation(summary = "관심 웹툰 조회", description =  "관심 웹툰 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/{user-id}/webtoons")
    public DataResponse<Object>  findUserWebtoon(
            @PathVariable("user-id") long userId,
            @RequestParam(name = "day",required = false) Integer dayId,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        long authUserId = userPrincipal.getId();

        List<WebtoonInfoDTO> webtoonInfoDTOS = userService.getUserWebtoonAll(authUserId, dayId);

        //조회된 데이터가 없으면 No Content
        if(webtoonInfoDTOS.isEmpty()){
            return responseService.getDataResponse(webtoonInfoDTOS, CustomSuccessStatus.RESPONSE_NO_CONTENT);
        }

        return responseService.getDataResponse(webtoonInfoDTOS, CustomSuccessStatus.RESPONSE_SUCCESS);

    }

    /*관심 웹툰 삭제*/
    @Tag(name = "회원 상호작용 기능")
    @Operation(summary = "관심 웹툰 삭제", description =  "관심 웹툰 삭제 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @DeleteMapping("/{user-id}/webtoons")
    public CommonResponse deleteUserWebtoon(
            @PathVariable("user-id") long userId,
            @RequestBody IdLongMultiSelectRequestDTO idLongMultiSelectRequestDTO,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        long authUserId = userPrincipal.getId();

        userService.deleteUserWebtoon(authUserId,idLongMultiSelectRequestDTO.getId());

        return responseService.getSuccessResponse();
    }

    /*선호 장르 선택*/
    @Tag(name = "회원 상호작용 기능")
    @Operation(summary = "선호 장르 선택", description =  "선호 장르 선택 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @PostMapping("/{user-id}/genre/select")
    public CommonResponse userSelectGenre(
            @PathVariable("user-id") long userId,
            @RequestBody GenreRequestDTO genreRequestDTO,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        long authUserId = userPrincipal.getId();

        userService.selectLikeGenre(authUserId,genreRequestDTO.getId());

        return responseService.getSuccessResponse();
    }

    //선호장르 조회
    @Tag(name = "회원 상호작용 기능")
    @Operation(summary = "선호 장르 조회", description =  "선호 장르 조회 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/{user-id}/genre/select")
    public DataResponse<GenreResponseDTO> findUserSelectGenre(
            @PathVariable("user-id") long userId,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        // TODO : 토큰 값과, user-id값이 동일한지 확인하는 로직 필요.
        long authUserId = userPrincipal.getId();
        System.out.println(authUserId);


        GenreResponseDTO selectLikeGenre = userService.findSelectLikeGenre(authUserId);
        if(selectLikeGenre.getId().size() == 0) responseService.getDataResponse(selectLikeGenre,CustomSuccessStatus.RESPONSE_NO_CONTENT);

        return responseService.getDataResponse(selectLikeGenre,CustomSuccessStatus.RESPONSE_SUCCESS);
    }


    /*선호 웹툰 선택*/
    @Tag(name = "회원 상호작용 기능")
    @Operation(summary = "선호 웹툰 선택", description =  "선호 웹툰 선택 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @PostMapping("/{user-id}/webtoon/select")
    public CommonResponse userSelectWebtoon(
            @PathVariable("user-id") long userId,
            @RequestBody WebtoonRequestDTO webtoonRequestDTO,
            @AuthenticationPrincipal UserPrincipal userPrincipal){


        long authUserId = userPrincipal.getId();

        userService.selectLikeWebtoon(authUserId,webtoonRequestDTO.getId());

        return responseService.getSuccessResponse();
    }


}
