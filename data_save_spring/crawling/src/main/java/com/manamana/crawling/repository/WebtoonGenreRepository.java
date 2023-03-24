package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.WebtoonGenre;
import com.manamana.crawling.entity.webtoon.WebtoonGenreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonGenreRepository extends JpaRepository<WebtoonGenre, WebtoonGenreId> {

}
