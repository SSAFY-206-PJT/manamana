package com.webtoon.manamana.webtoon.repository.webtoon;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.webtoon.QWebtoonAddition;
import com.webtoon.manamana.entity.webtoon.WebtoonAddition;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WebtoonAdditionRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public WebtoonAdditionRepositorySupport(JPAQueryFactory queryFactory) {
        super(WebtoonAddition.class);
        this.queryFactory = queryFactory;
    }

    //작품 추가정보 테이블 조회.
    public Optional<WebtoonAddition> findAdditionByWebtoonId(long webtoonId){

        QWebtoonAddition webtoonAddition = QWebtoonAddition.webtoonAddition;

        return Optional.ofNullable(
                queryFactory
                        .selectFrom(webtoonAddition)
                        .where(webtoonAddition.webtoon.id.eq(webtoonId))
                        .fetchOne()
        );

    }
}
