package com.manamana.crawling.service;

import com.manamana.crawling.dto.WebtoonDataDTO;
import com.manamana.crawling.dto.WebtoonDataArrayDTO;
import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.entity.webtoon.WebtoonProvider;
import com.manamana.crawling.repository.WebtoonProviderRepository;
import com.manamana.crawling.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WebtoonServiceImpl implements WebtoonService {

    private final WebtoonRepository webtoonRepository;
    private final WebtoonProviderRepository webtoonProviderRepository;

    public void webtoonsData(WebtoonDataArrayDTO webtoonDataArrayDTO, int providerId) {

    }

    /**
     * 웹툰 데이터 저장
     */
    public void saveWebtoon(WebtoonDataDTO webtoonDataDTO, int providerId) {

        Webtoon webtoon = Webtoon.builder()
                .name(webtoonDataDTO.getName())
                .imagePath(webtoonDataDTO.getImage())
                .plot(webtoonDataDTO.getPlot())
                .gradeId(webtoonDataDTO.getGrade())
                .serialId(webtoonDataDTO.getSerialId())
                .webtoonUrl(webtoonDataDTO.getWebtoonUrl())
                .webtoonId(webtoonDataDTO.getWebtoonId())
                .startDate(LocalDate.now())
                .totalEp(webtoonDataDTO.getTotalEp())
                .colorHsl(webtoonDataDTO.getColorHsl())
                .isDeleted(false)
                .providerId(web)
                .build();

        validateDuplicateWebtoon(webtoonDataDTO); //중복 웹툰 검증

        WebtoonProvider web = webtoonProviderRepository
                .findById(1)
                .orElseThrow();

        Webtoon webtoon = Webtoon.builder()
                .name(webtoonDataDTO.getName())
                .imagePath(webtoonDataDTO.getImagePath())
                .plot(webtoonDataDTO.getPlot())
                .gradeId(webtoonDataDTO.getGradeId())
                .serialId(webtoonDataDTO.getSerialId())
                .webtoonUrl(webtoonDataDTO.getWebtoonUrl())
                .webtoonId(webtoonDataDTO.getWebtoonId())
                .startDate(LocalDate.now())
                .totalEp(webtoonDataDTO.getTotalEp())
                .colorHsl(webtoonDataDTO.getColorHsl())
                .isDeleted(false)
                .providerId(web)
                .build();

//        Webtoon webtoon = createWebtoonDTO.createWebtoon();
        System.out.println("webtoon" + webtoon.getProviderId());
        webtoonRepository.save(webtoon);
    }

    //TODO webtoonId, webtoonProvder둘다 확인하기
    private void validateDuplicateWebtoon(WebtoonDataDTO webtoonDataDTO) {
        WebtoonProvider web = webtoonProviderRepository
                .findById(webtoonDataDTO.getProviderId())
                .orElseThrow();

        Optional<Webtoon> webtoon = webtoonRepository
                .findByWebtoonIdAndProviderId(webtoonDataDTO.getWebtoonId(), web);


        if(!webtoon.isEmpty()) {

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
    public Webtoon findOne(Long id) {
        return webtoonRepository
                .findById(id)
                .orElseThrow();
    }

    /**
     * 웹툰 단건 조회(webtoonId)
     */
    public Webtoon findOneByWebtoonId(String webtoonId) {
        Webtoon webtoon = webtoonRepository
                .findByWebtoonId(webtoonId)
                .orElseThrow();
        return webtoon;
    }

    public void saveWebtoons(WebtoonDataArrayDTO webtoonDataArrayDTO) {

        int providerId = webtoonDataArrayDTO.getProvider_id();

        webtoonDataArrayDTO.getData().forEach(w -> {
            saveWebtoon(w, providerId);
        });
    }
}
