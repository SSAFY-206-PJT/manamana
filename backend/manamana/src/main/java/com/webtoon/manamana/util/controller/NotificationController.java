package com.webtoon.manamana.util.controller;

import com.webtoon.manamana.auth.DTO.UserPrincipal;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.util.dto.request.NotificationRequestDTO;
import com.webtoon.manamana.util.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


//알림 객체 관련 sse

@Slf4j
@RequiredArgsConstructor
@RestController("/notification")
public class NotificationController {

    private final ResponseService responseService;
    private final NotificationService notificationService;

    @GetMapping(value = "/users/{user-id}/webtoons/{webtoon-id}", produces = "text/event-stream")
    public SseEmitter registerNotification(
            @PathVariable("user-id") long userId,
            @PathVariable("webtoon-id") long webtoonId,
            @AuthenticationPrincipal UserPrincipal userPrincipal){


        long authUserId = userPrincipal.getId();

        return notificationService.registNotification(authUserId,webtoonId);
    }

    @PostMapping("/event")
    public CommonResponse createNotification(
            @RequestBody NotificationRequestDTO notificationRequestDTO){


        notificationService.sendEvent(notificationRequestDTO.getIds());

        return responseService.getSuccessResponse();
    }
}
