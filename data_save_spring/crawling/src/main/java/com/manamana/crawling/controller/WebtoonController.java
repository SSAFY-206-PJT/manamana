package com.manamana.crawling.controller;

import com.manamana.crawling.dto.WebtoonDataArrayDTO;
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
    public Long saveWebtoon(@RequestBody WebtoonDataArrayDTO webtoonDataArrayDTO) {
        webtoonService.webtoonsData(webtoonDataArrayDTO);
        return 1L;
    }
}
