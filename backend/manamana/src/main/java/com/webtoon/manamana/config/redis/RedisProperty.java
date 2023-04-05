package com.webtoon.manamana.config.redis;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RedisProperty {

    //레디스 sse 객체 저장 키.
    @Value("${spring.redis.key}")
    public String sseKey;
}
