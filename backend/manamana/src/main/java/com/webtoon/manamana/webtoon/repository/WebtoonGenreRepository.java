package com.webtoon.manamana.webtoon.repository;

import com.webtoon.manamana.entity.webtoon.WebtoonGenre;
import com.webtoon.manamana.entity.webtoon.WebtoonGenreId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebtoonGenreRepository extends JpaRepository<WebtoonGenre, WebtoonGenreId> {

    List<WebtoonGenre> findByWebtoonId(long webtoonId);
}
