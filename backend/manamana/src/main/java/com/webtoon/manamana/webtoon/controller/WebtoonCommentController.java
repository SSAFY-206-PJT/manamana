package com.webtoon.manamana.webtoon.controller;

import com.webtoon.manamana.auth.DTO.UserPrincipal;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.webtoon.dto.request.CommentDeleteDTO;
import com.webtoon.manamana.webtoon.dto.request.CommentRequestDTO;
import com.webtoon.manamana.webtoon.dto.response.comment.CommentDTO;
import com.webtoon.manamana.webtoon.dto.response.comment.CommentListDTO;
import com.webtoon.manamana.webtoon.service.comment.WebtoonCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO : 전부 더미데이터라 바꿔야됨.

/**
 * 댓글 관련 컨트롤러 - 댓글 CRUD
 */
@Tag(name = "웹툰 댓글", description = "웹툰 댓글관련 API 모음")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/webtoons")
public class WebtoonCommentController {


    private final ResponseService responseService;
    private final WebtoonCommentService webtoonCommentService;


    /*댓글 전체 조회*/
    @Tag(name = "웹툰 댓글")
    @Operation(summary = "웹툰 댓글 조회", description =  "웹툰 댓글 조회 - 모든 댓글 조회, 페이징 가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/{webtoon-id}/comments")
    public DataResponse<List<CommentListDTO>> commentList(
            @PathVariable("webtoon-id") long webtoonId,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Pageable pageable){


        long authUserId = userPrincipal.getId();
        List<CommentListDTO> commentListDTOS = webtoonCommentService.findCommentAll(authUserId, webtoonId, pageable);

        if(commentListDTOS.isEmpty()) return responseService.getDataResponse(commentListDTOS,CustomSuccessStatus.RESPONSE_NO_CONTENT);


        return responseService.getDataResponse(commentListDTOS, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*댓글 작성*/
    @Tag(name = "웹툰 댓글")
    @Operation(summary = "웹툰 댓글 작성", description =  "웹툰 댓글 작성 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @PostMapping("/{webtoon-id}/comments")
    public DataResponse<CommentDTO> commentCreate(
            @PathVariable("webtoon-id") long webtoonId,
            @RequestBody CommentRequestDTO commentRequestDTO,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        long authUserId = userPrincipal.getId();

        CommentDTO comment = webtoonCommentService.createComment(authUserId, webtoonId, commentRequestDTO);

        return responseService.getDataResponse(comment,CustomSuccessStatus.RESPONSE_SUCCESS);

    }

    // TODO : 모든 내용을 바꾸고 있어서 PUT 으로 메서드 변경하는 것 고려 필요
    /*댓글 수정*/

    @Tag(name = "웹툰 댓글")
    @Operation(summary = "웹툰 댓글 수정", description =  "웹툰 댓글 수정 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @PatchMapping("/{webtoon-id}/comments/{comment-id}")
    public DataResponse<CommentDTO> updateComment(
            @PathVariable("webtoon-id") long webtoonId,
            @PathVariable("comment-id") long commentId,
            @RequestBody CommentRequestDTO commentRequestDTO,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        long authUserId = userPrincipal.getId();

        CommentDTO commentDTO = webtoonCommentService.updateComment(authUserId, webtoonId, commentId, commentRequestDTO);

        return responseService.getDataResponse(commentDTO,CustomSuccessStatus.RESPONSE_SUCCESS);

    }

    /*댓글 삭제*/
    @Tag(name = "웹툰 댓글")
    @Operation(summary = "웹툰 댓글 삭제", description =  "웹툰 댓글 삭제 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @DeleteMapping("/{webtoon-id}/comments")
    public CommonResponse deleteComment(
            @PathVariable("webtoon-id") long webtoonId,
            @RequestBody CommentDeleteDTO commentDeleteDTO,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

        long authUserId = userPrincipal.getId();

        webtoonCommentService.removeComment(authUserId,webtoonId,commentDeleteDTO.getId());


        return responseService.getSuccessResponse();

    }

}
