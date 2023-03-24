package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.codetable.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findAll();

    Optional<Genre> findById(int id);

    Optional<Genre> findByName(String name);
}
