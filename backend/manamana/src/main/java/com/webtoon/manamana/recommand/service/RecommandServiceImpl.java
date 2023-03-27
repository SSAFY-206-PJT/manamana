package com.webtoon.manamana.recommand.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service
@RequiredArgsConstructor
public class RecommandServiceImpl implements RecommandService {

    @Override
    public void recommandUserWebtoon() {

        /*
        DB에서 데이터 가져오는 로직
        */

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        ObjectMapper objectMapper = new ObjectMapper();


    }
}
