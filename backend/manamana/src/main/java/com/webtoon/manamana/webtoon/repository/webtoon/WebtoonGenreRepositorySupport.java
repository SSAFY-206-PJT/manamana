package com.webtoon.manamana.webtoon.repository.webtoon;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.webtoon.*;
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

    //주어진 장르에 포함되는 웹툰반환
    public List<WebtoonGenre> findWebtoonGenreInGenreId(List<Integer> genreId){

        QWebtoonGenre webtoonGenre = QWebtoonGenre.webtoonGenre;

        return queryFactory
                .selectFrom(webtoonGenre)
                .where(genreContain(genreId))
                .fetch();

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

    /* 장르별 웹툰 조회수 TOP 10 */
    public List<Long> findGenreWebtoonTOP10(int genreId) {

        return queryFactory
                .selectDistinct(QWebtoonGenre.webtoonGenre.webtoon.id)
                .from(QWebtoonGenre.webtoonGenre)
                .leftJoin(QWebtoonGenre.webtoonGenre.webtoon, QWebtoon.webtoon)
                .leftJoin(QWebtoon.webtoon.webtoonAddition, QWebtoonAddition.webtoonAddition)
                .where(QWebtoonGenre.webtoonGenre.genre.id.eq(genreId))
                .orderBy(QWebtoonAddition.webtoonAddition.view.desc())
                .limit(10)
                .fetch();
    }

    /* 특정 장르에 속하는 웹툰 조회 */
    public List<Long> findWebtoonAllByGenre(int genreId) {

        return queryFactory
                .select(QWebtoonGenre.webtoonGenre.webtoon.id)
                .from(QWebtoonGenre.webtoonGenre)
                .where(QWebtoonGenre.webtoonGenre.genre.id.eq(genreId))
                .fetch();
    }

    /*장르*/
    private BooleanExpression genreContain(List<Integer> genreId){

        if(genreId == null || genreId.isEmpty()) return null;

        return QWebtoonGenre.webtoonGenre.genre.id.in(genreId);
    }
}
