package com.manamana.crawling.controller;

import com.manamana.crawling.dto.ResponseRecommendDataDTO;
import com.manamana.crawling.dto.WebtoonDataArrayDTO;
import com.manamana.crawling.dto.WebtoonUpdateDTO;
import com.manamana.crawling.service.RecommendService;
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
    private final RecommendService recommendService;

    @PostMapping("/crawling")
    public Long saveWebtoon(@RequestBody WebtoonDataArrayDTO webtoonDataArrayDTO) {
        webtoonService.webtoonsData(webtoonDataArrayDTO);
        return 1L;
    }

    @GetMapping("/recommend")
    public ResponseRecommendDataDTO recommend() {
        return recommendService.dummyRecommendData();
    }

}
