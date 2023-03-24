package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.Author;
import com.manamana.crawling.entity.webtoon.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByNameAndWebtoon(String name, Webtoon webtoon);

    List<Author> findByWebtoon(Webtoon webtoon);
}
