package com.webtoon.manamana.user.service;

import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.user.WebtoonNotification;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.user.dto.response.WebtoonNotificationDTO;
import com.webtoon.manamana.user.repository.notification.WebtoonNotificationRepositorySupport;
import com.webtoon.manamana.user.repository.user.UserRepository;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserNotificationServiceImpl implements UserNotificationService{

    private final UserRepository userRepository;
    private final WebtoonRepository webtoonRepository;

    private final WebtoonNotificationRepositorySupport webtoonNotificationRepositorySupport;


    /*유저 알림 조회*/
    @Override
    public List<WebtoonNotificationDTO> getWebtoonNotificationList(long userId) {

        //회원 정보 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_USER));

        //알람 조회
        List<WebtoonNotification> webtoonNotifications = webtoonNotificationRepositorySupport.findWebtoonNotificationByUserAll(user);

        //DTO 변환
        List<WebtoonNotificationDTO> webtoonNotificationDTOS = webtoonNotifications.stream()
                .map(WebtoonNotificationDTO::createDTO)
                .collect(Collectors.toList());

        return webtoonNotificationDTOS;
    }

    /*유저 알림 제거*/
    @Override
    public void removeWebtoonNotification(long userId, long webtoonId) {

        //회원 정보 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_USER));

        //해당 웹툰 조회.
        Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_WEBTOON));

        //알림 조회
        WebtoonNotification webtoonNotification = webtoonNotificationRepositorySupport.findWebtoonNotificationByUserAndWebtoon(user, webtoon)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUND_NOTIFICATION));

        //삭제처리.
        webtoonNotification.deleteNotification();

    }
}
