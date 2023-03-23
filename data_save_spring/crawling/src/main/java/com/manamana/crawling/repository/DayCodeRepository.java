package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.codetable.DayCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DayCodeRepository extends JpaRepository<DayCode, Integer> {
    List<DayCode> findAll();
}
