package com.webtoon.manamana.util.repository;

import com.webtoon.manamana.entity.webtoon.WebtoonProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonProviderRepository extends JpaRepository<WebtoonProvider,Integer> {
}
