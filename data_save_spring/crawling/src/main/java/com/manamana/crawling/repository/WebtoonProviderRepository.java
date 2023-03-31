package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.WebtoonProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public interface WebtoonProviderRepository extends JpaRepository<WebtoonProvider, Integer> {

    Optional<WebtoonProvider> findById(int id);

}
