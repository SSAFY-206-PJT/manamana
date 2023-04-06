package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.WebtoonProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebtoonProviderRepository extends JpaRepository<WebtoonProvider, Integer> {

    Optional<WebtoonProvider> findById(int id);

}
