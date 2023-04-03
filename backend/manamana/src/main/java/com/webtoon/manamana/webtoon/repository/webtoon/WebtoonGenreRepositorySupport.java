package com.webtoon.manamana.webtoon.repository.webtoon;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.webtoon.QWebtoonGenre;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.entity.webtoon.WebtoonGenre;
import com.webtoon.manamana.entity.webtoon.codetable.QGenre;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WebtoonGenreRepositorySupport extends QuerydslRepositorySupport {


    private final JPAQueryFactory queryFactory;

    public WebtoonGenreRepositorySupport(JPAQueryFactory queryFactory) {
        super(WebtoonGenre.class);
        this.queryFactory = queryFactory;
    }


    /*특정 웹툰에 속하는 장르 조회.*/
    public List<WebtoonGenre> findGenrebyWebtoonAll(long webtoonId){

        return queryFactory
                .selectFrom(QWebtoonGenre.webtoonGenre)
                .leftJoin(QWebtoonGenre.webtoonGenre.genre, QGenre.genre)
                .fetchJoin()
                .where(QWebtoonGenre.webtoonGenre.webtoon.id.eq(webtoonId))
                .fetch();
    }

}
