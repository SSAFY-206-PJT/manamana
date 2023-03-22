package com.manamana.crawling.service;

import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.entity.webtoon.WebtoonProvider;
import com.manamana.crawling.repository.WebtoonProviderRepository;
import com.manamana.crawling.repository.WebtoonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WebtoonServiceTest {


    @Autowired
    WebtoonService webtoonService;
    @Autowired
    WebtoonRepository webtoonRepository;

    @Autowired
    WebtoonProviderRepository webtoonProviderRepository;

    @Test
    public void 웹툰저장() throws Exception {
        //given
        Webtoon webtoon = Webtoon.builder()
                .id(1)
                .name("웹툰")
                .imagePath("123")
                .plot("줄거리")
                .gradeId(1)
                .serialId(1)
                .webtoonUrl("222")
                .webtoonId("333")
                .startDate(LocalDate.now())
                .totalEp(111)
                .colorHsl("123")
                .isDeleted(false)
                .providerId(webtoonProviderRepository.findOne(1))
                .build();

        //when
        Long saveId = webtoonService.saveWebtoon(webtoon);
        System.out.println(saveId);
        //then
        System.out.println(webtoonRepository.findOne(saveId));
        assertEquals(webtoon, webtoonRepository.findOne(saveId));


    }

    @Test
    public void 중복_웹툰_예외() throws Exception {
        //given

        //when

        //then
    }
}