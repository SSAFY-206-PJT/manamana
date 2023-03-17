package com.webtoon.manamana.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.user.QUser;
import com.webtoon.manamana.entity.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;
    

    public UserRepositorySupport(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    public List<User> findByNickname(String nickname){
        return queryFactory
                .selectFrom(QUser.user)
                .where(QUser.user.nickname.eq(nickname))
                .fetch();
    }

}
