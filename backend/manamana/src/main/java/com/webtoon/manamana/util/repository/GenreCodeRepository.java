package com.webtoon.manamana.util.repository;

import com.webtoon.manamana.entity.webtoon.codetable.DayCode;
import com.webtoon.manamana.entity.webtoon.codetable.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/*장르 코드화 테이블*/
public interface GenreCodeRepository extends JpaRepository<Genre,Integer> {
}
