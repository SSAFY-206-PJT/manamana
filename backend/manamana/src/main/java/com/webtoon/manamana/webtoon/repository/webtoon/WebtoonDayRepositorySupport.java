package com.webtoon.manamana.webtoon.repository.webtoon;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.webtoon.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WebtoonDayRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public WebtoonDayRepositorySupport(JPAQueryFactory queryFactory) {
        super(WebtoonDay.class);
        this.queryFactory = queryFactory;
    }

    //웹툰 요일에 해당하는 값 조회
    public List<WebtoonDay> findWebtoonDayInCodeId(List<Integer> dayId){

        QWebtoonDay webtoonDay = QWebtoonDay.webtoonDay;

        return queryFactory
                .selectFrom(webtoonDay)
                .where(dayContain(dayId))
                .fetch();
    }


    private BooleanExpression dayContain(List<Integer> dayId){

        if(dayId == null || dayId.isEmpty()) return null;

        return QWebtoonDay.webtoonDay.codeId.in(dayId);

    }
}
