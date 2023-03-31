package com.webtoon.manamana.user.repository.user;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.user.QUserGenre;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.user.UserGenre;
import com.webtoon.manamana.entity.webtoon.Comment;
import com.webtoon.manamana.entity.webtoon.codetable.Genre;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserGenreRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public UserGenreRepositorySupport(JPAQueryFactory queryFactory) {
        super(UserGenre.class);
        this.queryFactory = queryFactory;
    }

    public List<UserGenre> findUserGenreAll(User user, Genre genre){

        QUserGenre userGenre = QUserGenre.userGenre;

        return queryFactory
                .selectFrom(userGenre)
                .where(userGenre.user.eq(user), userGenre.genre.eq(genre))
                .fetch();
    }

}
