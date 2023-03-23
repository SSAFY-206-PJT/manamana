package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.entity.webtoon.WebtoonProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {

<<<<<<< HEAD
    Optional<Webtoon> findById(Long id);

    List<Webtoon> findAll();

    Optional<Webtoon> findByWebtoonId(String webtoonId);

=======

    Optional<Webtoon> findById(Long id);

    List<Webtoon> findAll();

    Optional<Webtoon> findByWebtoonId(String webtoonId);

>>>>>>> 6b44a1aecf1d6faa1802c83e1ce09ba65b1953f1
    Optional<Webtoon> findByWebtoonIdAndProviderId(String webtoonId, WebtoonProvider ProviderId);

}
