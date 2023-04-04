package com.webtoon.manamana.util.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface NotificationService {

    /*알람 등록*/
    SseEmitter registNotification(long userId, long webtoonId);

    /*이벤트 발생.*/
    void sendEvent(List<Long> webtoonIds);
}
