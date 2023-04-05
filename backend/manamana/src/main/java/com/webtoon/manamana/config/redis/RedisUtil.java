package com.webtoon.manamana.config.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class RedisUtil {

    private final RedisTemplate<String,Object> redisTemplate;


    // TODO: 2023-04-04 - SSE 객체를 레디스에 저장하도록 변경해야됨.

    public void setData(String redisKey, Long webtoonId, Map<Long, SseEmitter> sseObject) {

        try{

            String webtoonIdStr = String.valueOf(webtoonId);

            //해당 객체를 레디스에 넣을 수 있도록 변환.
            ObjectMapper objectMapper = new ObjectMapper();

            String s = objectMapper.writeValueAsString(sseObject);

            //sseMap 넣기
            redisTemplate.opsForHash().put(redisKey,webtoonIdStr,s);
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }

    }

    public Map<Long, SseEmitter> getData(String redisKey, Long webtoonId){

        String webtoonIdStr = String.valueOf(webtoonId);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = (String) redisTemplate.opsForHash().get(redisKey, webtoonIdStr);

        //오브젝트 매퍼로 변환하기 전에 변환할 타입 저장.
        TypeReference<HashMap<Long, SseEmitter>> typeRef = new TypeReference<>() {};

        HashMap<Long, SseEmitter> sseEmitterHashMap = null;
        try{
            //key - 유저id, value 유저가 가지고 있는 해시
            sseEmitterHashMap = objectMapper.readValue(s, typeRef);
        }
        catch(IOException e){
            //커스텀 예외를 던지지 않음 - 변환실패는 로직자체가 실패했다는 뜻.
            e.printStackTrace();
        }

        return sseEmitterHashMap;
    }

}
