package com.manamana.crawling.controller;

import com.manamana.crawling.dto.WebtoonDataDTO;
import com.manamana.crawling.dto.WebtoonDataArrayDTO;
import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
public class WebtoonController {

    private final WebtoonService webtoonService;

<<<<<<< HEAD
//    @GetMapping("/crawling")
//    public Webtoon getWebtoon() {
//        return webtoonService.findOne(2L);
//    }

    @PostMapping("/crawling")
    public Long saveWebtoon(@RequestBody WebtoonDataArrayDTO webtoonDataArrayDTO) {
=======
    @GetMapping("/crawling")
    public Webtoon getWebtoon() {
        return webtoonService.findOne(2L);
    }

    @PostMapping("/crawling")
    public Long saveWebtoon(@RequestBody WebtoonDataDTO requestDTO) {
        webtoonService.saveWebtoon(requestDTO);
        return 1L;
    }

    @PostMapping("/crawlings")
    public Long saveWebtoons(@RequestBody WebtoonDataArrayDTO webtoonDataArrayDTO) {
>>>>>>> 6b44a1aecf1d6faa1802c83e1ce09ba65b1953f1
        webtoonService.webtoonsData(webtoonDataArrayDTO);
        return 1L;
    }

}
