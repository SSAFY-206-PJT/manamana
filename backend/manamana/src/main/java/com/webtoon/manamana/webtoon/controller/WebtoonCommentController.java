package com.webtoon.manamana.webtoon.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.webtoon.dto.request.CommentDeleteDTO;
import com.webtoon.manamana.webtoon.dto.request.CommentRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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


    /*댓글 전체 조회*/
    @Tag(name = "웹툰 댓글")
    @Operation(summary = "웹툰 댓글 조회", description =  "웹툰 댓글 조회 - 모든 댓글 조회, 페이징 가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @GetMapping("/{webtoon-id}/comments")
    public DataResponse<Object> commentList(
            @PathVariable("webtoon-id") long webtoonId,
            Pageable pageable) throws Exception{

        log.info("page = {}, size = {}", pageable.getOffset(), pageable.getPageSize());

        String temp1 = "{\n" +
                "\t\t\t\"id\": 1,\n" +
                "\t\t\t\"content\": \"댓글 내용입니다.\",\n" +
                "\t\t\t\"isSpoiler\": true,\n" +
                "\t\t\t\"report\": 0,\n" +
                "\t\t\t\"createTime\": \"2023.03.13\",\n" +
                "\t\t\t\"user\": {\n" +
                "\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\"nickname\": \"test\",\n" +
                "\t\t\t\t\"imagePath\": \"url\"\n" +
                "\t\t\t}\n" +
                "\t\t}";

        String temp2 = "{\n" +
                "\t\t\t\"id\": 2,\n" +
                "\t\t\t\"content\": \"댓글 내용입니다222.\",\n" +
                "\t\t\t\"isSpoiler\": false,\n" +
                "\t\t\t\"report\": 0,\n" +
                "\t\t\t\"createTime\": \"2023.03.12\",\n" +
                "\t\t\t\"user\": {\n" +
                "\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\"nickname\": \"test2222\",\n" +
                "\t\t\t\t\"imagePath\": \"url12312\"\n" +
                "\t\t\t}\n" +
                "\t\t}";

        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(temp1);
        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(temp2);

        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);

        log.info("[댓글 전체 조회 확인] - webtoon-id : {} ", webtoonId);

        return responseService.getDataResponse(jsonArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    /*댓글 작성*/
    @Tag(name = "웹툰 댓글")
    @Operation(summary = "웹툰 댓글 작성", description =  "웹툰 댓글 작성 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "API 정상 동작"),
            @ApiResponse(responseCode = "400",description = "API 에러"),
    })
    @PostMapping("/{webtoon-id}/comments")
    public DataResponse<Object> commentCreate(
            @PathVariable("webtoon-id") long webtoonId,
            @RequestBody CommentRequestDTO commentRequestDTO
            )throws Exception{

        String temp = "{\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"content\" : \""+commentRequestDTO.getContent()+"\",\n" +
                "\t\t\"isSpoiler\" : "+commentRequestDTO.isSpoiler()+ "\n" +
                "\t}";

        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj = (JSONObject) jsonParser.parse(temp);

        log.info("[댓글 작성 확인] - webtoon-id : {}", webtoonId);

        return responseService.getDataResponse(jsonObj,CustomSuccessStatus.RESPONSE_SUCCESS);

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
    public DataResponse<Object> updateComment(
            @PathVariable("webtoon-id") long webtoonId,
            @PathVariable("comment-id") long commentId,
            @RequestBody CommentRequestDTO commentRequestDTO
    ) throws Exception{
        String temp = "{\n" +
                "\t\t\"id\":" +commentId+",\n" +
                "\t\t\"content\" : \""+commentRequestDTO.getContent()+"\",\n" +
                "\t\t\"isSpoiler\" : "+commentRequestDTO.isSpoiler()+ "\n" +
                "\t}";

        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObj = (JSONObject) jsonParser.parse(temp);

        log.info("[댓글 수정 확인] - webtoon-id : {} , comment-id : {}", webtoonId,commentId);


        return responseService.getDataResponse(jsonObj,CustomSuccessStatus.RESPONSE_SUCCESS);

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
            @RequestBody CommentDeleteDTO commentDeleteDTO
            ){

        log.info(commentDeleteDTO.getId().toString());
        log.info("[신고 확인] - webtoon-id : {}", webtoonId);
        return responseService.getSuccessResponse();

    }
}
