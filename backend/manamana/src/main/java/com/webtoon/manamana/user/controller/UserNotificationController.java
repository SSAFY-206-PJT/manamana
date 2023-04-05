package com.webtoon.manamana.user.controller;

import com.webtoon.manamana.auth.dto.UserPrincipal;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.user.dto.response.WebtoonNotificationDTO;
import com.webtoon.manamana.user.service.UserNotificationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserNotificationController {


    private final ResponseService responseService;
    private final UserNotificationService userNotificationService;

    /*유저가 받은 알람*/
    @GetMapping("/{user-id}/webtoon/alarm")
    public DataResponse<List<WebtoonNotificationDTO>> getUserNotification(
            @PathVariable("user-id") long userId,
            @AuthenticationPrincipal UserPrincipal userPrincipal){

//        long authUserId = userPrincipal.getId();

        long authUserId = 1L;

        List<WebtoonNotificationDTO> webtoonNotificationList = userNotificationService.getWebtoonNotificationList(authUserId);
        if(webtoonNotificationList.isEmpty()) return responseService.getDataResponse(webtoonNotificationList,CustomSuccessStatus.RESPONSE_NO_CONTENT);


        return responseService.getDataResponse(webtoonNotificationList, CustomSuccessStatus.RESPONSE_SUCCESS);
    }


    /*유저가 받은 알람 제거*/
    @DeleteMapping("/{user-id}/webtoons/{webtoon-id}/alarm")
    public CommonResponse deleteUserNotification(
            @PathVariable("user-id") long userId,
            @PathVariable("webtoon-id") long webtoonId,
            @AuthenticationPrincipal UserPrincipal userPrincipal){


        //jwt로 인증한 값 가져오기.
//        long authUserId = userPrincipal.getId();

        long authUserId = 1L;

        //삭제 서비스 호출
        userNotificationService.removeWebtoonNotification(authUserId,webtoonId);


        return responseService.getSuccessResponse();

    }

}
