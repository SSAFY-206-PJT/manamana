package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.entity.webtoon.WebtoonProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebtoonProviderRepository  {

    private final EntityManager em;

    public void saveProvider(WebtoonProvider webtoonProvider) {
        em.persist(webtoonProvider);
    }

    public WebtoonProvider findOne(int id) {
        return em.find(WebtoonProvider.class, id);
    }


}
