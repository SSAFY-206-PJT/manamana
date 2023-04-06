package com.webtoon.manamana.util.repository;

import com.webtoon.manamana.entity.webtoon.codetable.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

/*연재 등급 코드화 테이블*/
public interface GradeCodeRepository extends JpaRepository<Grade,Integer> {
}
