package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.Webtoon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebtoonRepository {

    private final EntityManager em;

    public void saveWebtoon(Webtoon webtoon) {
//        em.persist(webtoon);
        em.merge(webtoon);
    }

    public Webtoon findOne(Long id) {
        return em.find(Webtoon.class, id);
    }

    public List<Webtoon> findAll() {
        return em.createQuery("select w from Webtoon w", Webtoon.class)
                .getResultList();
    }

    public List<Webtoon> findByWebtoonId(String webtoonId) {
        return em.createQuery("select w from Webtoon w where w.webtoonId = :webtoonId", Webtoon.class)
                .setParameter("webtoonId", webtoonId)
                .getResultList();
    }
}
