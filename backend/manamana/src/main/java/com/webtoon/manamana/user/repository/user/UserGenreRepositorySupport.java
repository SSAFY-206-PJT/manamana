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
import java.util.Optional;

@Repository
public class UserGenreRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public UserGenreRepositorySupport(JPAQueryFactory queryFactory) {
        super(UserGenre.class);
        this.queryFactory = queryFactory;
    }

    //해당 웹툰에 속해있는 모든 장르 조회


    public Optional<UserGenre> findUserGenre(User user, Genre genre){

        QUserGenre userGenre = QUserGenre.userGenre;

        return Optional.ofNullable(
                queryFactory
                .selectFrom(userGenre)
                .where(userGenre.user.eq(user), userGenre.genre.eq(genre))
                .fetchOne()
        );
    }

}
