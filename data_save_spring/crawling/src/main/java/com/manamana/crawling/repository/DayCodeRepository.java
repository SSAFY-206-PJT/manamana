package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.codetable.DayCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DayCodeRepository extends JpaRepository<DayCode, Integer> {
    Optional<DayCode> findByDay(String Day);
}
