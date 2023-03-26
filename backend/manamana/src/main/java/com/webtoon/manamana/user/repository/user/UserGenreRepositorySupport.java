package com.webtoon.manamana.user.repository.user;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.user.UserGenre;
import com.webtoon.manamana.entity.webtoon.Comment;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserGenreRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public UserGenreRepositorySupport(JPAQueryFactory queryFactory) {
        super(UserGenre.class);
        this.queryFactory = queryFactory;
    }

}
