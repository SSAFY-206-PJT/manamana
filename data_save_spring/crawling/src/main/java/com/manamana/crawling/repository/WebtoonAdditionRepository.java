package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.entity.webtoon.WebtoonAddition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebtoonAdditionRepository extends JpaRepository<WebtoonAddition, Long> {
    Optional<WebtoonAddition> findByWebtoon(Webtoon webtoon);
}
