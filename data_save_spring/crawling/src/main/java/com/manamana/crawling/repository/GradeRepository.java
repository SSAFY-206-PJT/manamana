package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.codetable.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    List<Grade> findAll();
}
