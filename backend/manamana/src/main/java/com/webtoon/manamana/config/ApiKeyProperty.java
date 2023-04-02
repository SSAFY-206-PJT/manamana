package com.webtoon.manamana.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//api 설정 키값.
@Getter
@Component
public class ApiKeyProperty {

    @Value("${openApi.naver.url}")
    private String openApiUrl;

    @Value("${openApi.naver.client-id}")
    private String clientId;

    @Value("${openApi.naver.client-secret}")
    private String clientSecret;

    @Value("${openApi.naver.keyword-rank.url}")
    private String keywordRankUrl;
    @Value("${openApi.naver.keyword-rank.cid}")
    private String keywordRankCid;
    @Value("${openApi.naver.keyword-rank.timeUnit}")
    private String keywordRankTimeUnit;

    @Value("${openApi.naver.keyword-rank.header.user-agent}")
    private String keywordRankHeaderUserAgent;
    @Value("${openApi.naver.keyword-rank.header.referer}")
    private String keywordRankHeaderReferer;
}
