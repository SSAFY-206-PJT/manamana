package com.manamana.crawling.controller;

import com.manamana.crawling.config.response.ResponseService;
import com.manamana.crawling.dto.request.CreateWebtoonDTO;
import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
public class WebtoonController {

    private final WebtoonService webtoonService;

    @PostMapping("/crawling")
    public Long save(@RequestBody CreateWebtoonDTO requestDTO) {
        CreateWebtoonDTO requests = requestDTO;
        System.out.println(1);
        System.out.println(requests);
        return webtoonService.saveWebtoon(requests.createWebtoon());
    }
}
