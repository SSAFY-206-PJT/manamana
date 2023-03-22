package com.manamana.crawling.service;

import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    /**
     * 웹툰 데이터 저장
     */
    public Long saveWebtoon(Webtoon webtoon) {
        //validateDuplicateWebtoon(webtoon); //중복 웹툰 검증
        webtoonRepository.saveWebtoon(webtoon);
        return webtoon.getId();
    }

    //TODO webtoonId, webtoonProvder둘다 확인하기
    private void validateDuplicateWebtoon(Webtoon webtoon) {
        List<Webtoon> findWebtoon = webtoonRepository.findByWebtoonId(webtoon.getWebtoonId());
        if (!findWebtoon.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 웹툰입니다.");
        }
    }

    /**
     * 웹툰 전체 조회
     */
    public List<Webtoon> findWebtoon() {
        return webtoonRepository.findAll();
    }

    /**
     * 웹툰 단건 조회(id)
     */
    public Webtoon findOne(Long webtoonId) {
        return webtoonRepository.findOne(webtoonId);
    }

    /**
     * 웹툰 단건 조회(webtoonId)
     */
    public List<Webtoon> findOneByWebtoonId(String webtoonId) {
        return webtoonRepository.findByWebtoonId(webtoonId);
    }
}
