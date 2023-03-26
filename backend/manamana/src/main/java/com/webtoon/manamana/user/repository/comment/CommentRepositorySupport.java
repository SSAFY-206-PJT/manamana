package com.webtoon.manamana.user.repository.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.webtoon.Comment;
import com.webtoon.manamana.entity.webtoon.QComment;
import com.webtoon.manamana.entity.webtoon.QWebtoon;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;


    public CommentRepositorySupport(JPAQueryFactory queryFactory) {
        super(Comment.class);
        this.queryFactory = queryFactory;

    }

    //유저가 작성한 댓글 목록
    public List<Comment> findByUserCommentAll(User user){

        return queryFactory
                .selectFrom(QComment.comment)
                .where(QComment.comment.user.eq(user))
                .leftJoin(QComment.comment.webtoon, QWebtoon.webtoon)
                .fetchJoin()
                .distinct().fetch();
    }
}
