package com.webtoon.manamana.user.repository.notification;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.user.QWebtoonNotification;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.user.WebtoonNotification;
import com.webtoon.manamana.entity.webtoon.QWebtoon;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WebtoonNotificationRepositorySupport extends QuerydslRepositorySupport {


    private final JPAQueryFactory queryFactory;

    public WebtoonNotificationRepositorySupport(JPAQueryFactory queryFactory) {
        super(WebtoonNotification.class);
        this.queryFactory = queryFactory;
    }

    /*유저의 알람 전체 조회*/
    List<WebtoonNotification> getWebtoonNotificationByUserAll(User user){

        QWebtoonNotification webtoonNotification = QWebtoonNotification.webtoonNotification;
        return queryFactory
                .selectFrom(webtoonNotification)
                .leftJoin(webtoonNotification.webtoon, QWebtoon.webtoon)
                .fetchJoin()
                .where(webtoonNotification.isChecked.eq(false), webtoonNotification.user.eq(user))
                .fetch();
    }

    /*유저의 알람에 해당하는 정보 조회*/
    Optional<WebtoonNotification> getWebtoonNotificationByUserAndWebtoon(User user, Webtoon webtoon){

        QWebtoonNotification webtoonNotification = QWebtoonNotification.webtoonNotification;
        return Optional.ofNullable(
                queryFactory
                .selectFrom(webtoonNotification)
                .where(webtoonNotification.user.eq(user),
                        webtoonNotification.webtoon.eq(webtoon),
                        webtoonNotification.isChecked.eq(false))
                .fetchOne()
        );
    }
}
