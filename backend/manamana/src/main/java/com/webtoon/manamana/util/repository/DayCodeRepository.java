package com.webtoon.manamana.util.repository;

import com.webtoon.manamana.entity.webtoon.codetable.DayCode;
import org.springframework.data.jpa.repository.JpaRepository;

/*요일 코드화 테이블*/
public interface DayCodeRepository extends JpaRepository<DayCode,Integer> {

}
