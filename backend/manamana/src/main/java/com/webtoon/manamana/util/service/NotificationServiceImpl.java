package com.webtoon.manamana.util.service;

import com.webtoon.manamana.config.redis.RedisProperty;
import com.webtoon.manamana.config.redis.RedisUtil;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.user.WebtoonNotification;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.user.repository.notification.WebtoonNotificationRepository;
import com.webtoon.manamana.user.repository.notification.WebtoonNotificationRepositorySupport;
import com.webtoon.manamana.user.repository.user.UserRepository;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class NotificationServiceImpl implements NotificationService{

    //레디스
    private final RedisUtil redisUtil;

    //레디스 설정
    private final RedisProperty redisProperty;

    private final WebtoonRepository webtoonRepository;

    private final UserRepository userRepository;

    private final WebtoonNotificationRepository webtoonNotificationRepository;
    private final WebtoonNotificationRepositorySupport webtoonNotificationRepositorySupport;


    @Override
    public SseEmitter registNotification(long userId, long webtoonId) {

        //sse 객체를 생성해서 클라이언트가 구독하도록 함.
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

        try{
            sseEmitter.send(SseEmitter.event().name("connect")); //연결되면 메시지 하나 보냄, 안보내면 재연결시 500에러남.
        }
        catch (
                IOException e){
            throw new CustomException(CustomExceptionStatus.FAIL_CONNECT_SSE);
        }

        //레디스에서 조회
        Map<Long, SseEmitter> sseMap = redisUtil.getData(redisProperty.getSseKey(), webtoonId);

        //조회된 데이터가 없으면 맵을 생성하고 객체를 넣음
        if(sseMap == null){
            sseMap = new HashMap<>();
            sseMap.put(userId,sseEmitter);
        }
        //조회된 데이터가 있고, 해당 유저가 없으면,객체 추가.
        else if(!sseMap.containsKey(userId)){
            sseMap.put(userId, sseEmitter);
        }
        //조회된 데이터가 있는데, 이미 해당 유저가 있으면 에러
        else{
            throw new CustomException(CustomExceptionStatus.ALREADY_NOTIFICATION);
        }

        //만들어진 맵 객체를 레디스에 저장함.
        redisUtil.setData(redisProperty.getSseKey(),webtoonId,sseMap);

        return sseEmitter;
    }

    //이벤트 보내기 + 알림 저장.
    @Override
    public void sendEvent(List<Long> webtoonIds) {

        webtoonIds.forEach(webtoonId -> {

            //해당 웹툰이 조회되는지 확인.
            Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(webtoonId)
                    .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_WEBTOON));

            //레디스에서 조회
            Map<Long, SseEmitter> data = redisUtil.getData(redisProperty.getSseKey(), webtoonId);

            //유저를 돌면서 이벤트 발생.
            data.keySet().forEach(userId -> {

                //유저 조회
                User user = userRepository.findByIdAndIsDeletedFalse(userId)
                        .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_USER));

                //발생한 알림 저장.
                WebtoonNotification webtoonNotification = WebtoonNotification.createWebtoonNotification(webtoon.getTotalEp(), user, webtoon);
                webtoonNotificationRepository.save(webtoonNotification);


                //레디스에서 객체 조회.
                SseEmitter sseEmitter = data.get(userId);
                try{
                    sseEmitter.send(SseEmitter.event().name("notification").data(webtoon.getName()+"\n" + webtoonNotification.getEpisode()+"회차가 등록되었습니다."));
                } catch(Exception e){
                    //이벤트 조회에 문제가 생기면 해당 객체 삭제
                    data.remove(userId);
                }

            });

            //삭제되었을수도 있으니 다시 저장.
            redisUtil.setData(redisProperty.getSseKey(),webtoonId,data);

        });

    }



}
