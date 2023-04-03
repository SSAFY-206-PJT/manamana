package com.webtoon.manamana.util.repository;

import com.webtoon.manamana.entity.webtoon.codetable.DayCode;
import com.webtoon.manamana.entity.webtoon.codetable.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/*정렬 코드화 테이블*/
public interface SortCodeRepository extends JpaRepository<Sort,Integer> {


}
