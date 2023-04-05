package com.webtoon.manamana.user.service;

import com.webtoon.manamana.entity.user.WebtoonNotification;
import com.webtoon.manamana.user.dto.response.WebtoonNotificationDTO;

import java.util.List;

public interface UserNotificationService{


    /*유저 알림 조회*/
    List<WebtoonNotificationDTO> getWebtoonNotificationList(long userId);

    /*유저 알림 제거*/
    void removeWebtoonNotification(long userId, long webtoonId);



}