package com.webtoon.manamana.util.repository;

import com.webtoon.manamana.entity.webtoon.codetable.DayCode;
import com.webtoon.manamana.entity.webtoon.codetable.SerialStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/*연재 여부 테이블 코드*/
public interface StatusCodeRepository extends JpaRepository<SerialStatus,Integer> {
}
