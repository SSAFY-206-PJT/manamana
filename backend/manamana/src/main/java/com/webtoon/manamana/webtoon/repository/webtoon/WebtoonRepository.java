package com.webtoon.manamana.webtoon.repository.webtoon;

import com.webtoon.manamana.entity.webtoon.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebtoonRepository extends JpaRepository<Webtoon,Long> {

    Optional<Webtoon> findByIdAndIsDeletedFalse(long webtoonId);


}
