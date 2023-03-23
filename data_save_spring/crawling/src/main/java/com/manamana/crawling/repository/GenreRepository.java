package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.codetable.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findAll();

    Optional<Genre> findById(int id);

    Optional<Genre> findByName(String name);
=======

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findAll();
>>>>>>> 6b44a1aecf1d6faa1802c83e1ce09ba65b1953f1
}
