package com.webtoon.manamana.user.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.user.LoginProvider;
import com.webtoon.manamana.entity.user.QLoginProvider;
import com.webtoon.manamana.entity.user.QUser;
import com.webtoon.manamana.entity.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public UserRepositorySupport(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    //유저와 provider 같이 조회
    public Optional<User> findUserByEmailAndLoginProvider(String email, LoginProvider loginProvider){

        QUser user = QUser.user;
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(user)
                        .leftJoin(user.loginProvider, QLoginProvider.loginProvider)
                        .fetchJoin()
                        .where(user.email.eq(email), user.loginProvider.eq(loginProvider))
                        .fetchOne()
        );
    }

//    public List<User> findByNickname(String nickname){
//        return queryFactory
//                .selectFrom(QUser.user)
//                .where(QUser.user.nickname.eq(nickname))
//                .fetch();
//    }

    public List<Long> findUserIdByAge(int age) {

        QUser user = QUser.user;

        return queryFactory
                .select(user.id)
                .from(user)
                .where(user.age.eq(age))
                .fetch();
    }

    public List<Long> findUserIdByGender(String gender) {

        QUser user = QUser.user;

        return queryFactory
                .select(user.id)
                .from(user)
                .where(user.gender.eq(gender))
                .fetch();
    }

}
