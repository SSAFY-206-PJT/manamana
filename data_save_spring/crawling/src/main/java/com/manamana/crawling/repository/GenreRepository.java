package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.codetable.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findAll();
}
