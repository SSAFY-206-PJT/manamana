package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.entity.webtoon.WebtoonDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebtoonDayRepository extends JpaRepository<WebtoonDay, Integer> {
    List<WebtoonDay> findByWebtoon(Webtoon webtoon);

    Optional<WebtoonDay> findByCodeIdAndWebtoon(int codeId, Webtoon webtoon);
}
