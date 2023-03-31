package com.webtoon.manamana.user.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.user.PreferGenre;
import com.webtoon.manamana.entity.user.QPreferGenre;
import com.webtoon.manamana.entity.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PreferGenreRepositorySupport extends QuerydslRepositorySupport {


    private final JPAQueryFactory queryFactory;

    public PreferGenreRepositorySupport(JPAQueryFactory queryFactory) {
        super(PreferGenreRepository.class);
        this.queryFactory = queryFactory;
    }

    /*선호했던& 선호체크되어있는 장르 조회*/
    public List<PreferGenre> findSelectGenreAll(User user){

        QPreferGenre preferGenre = QPreferGenre.preferGenre;

        return queryFactory
                .selectFrom(preferGenre)
                .where(preferGenre.user.eq(user))
                .fetch();
    }

    /*선호했던 장르 조회.*/
    public List<PreferGenre> findSelectGenre(User user){

        QPreferGenre preferGenre = QPreferGenre.preferGenre;

        return queryFactory
                .selectFrom(preferGenre)
                .where(preferGenre.user.eq(user), preferGenre.isCanceled.eq(false))
                .fetch();
    }

    //유저가 선택한 장르.
    public Optional<PreferGenre> findSelectGenreOne(User user, int genreId){

        QPreferGenre preferGenre = QPreferGenre.preferGenre;

        return Optional.ofNullable(queryFactory
                .selectFrom(preferGenre)
                .where(preferGenre.user.eq(user), preferGenre.genreId.eq(genreId))
                .fetchOne());


    }
}
