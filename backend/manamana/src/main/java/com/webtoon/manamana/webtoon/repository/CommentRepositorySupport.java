package com.webtoon.manamana.webtoon.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.user.QUser;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.webtoon.Comment;
import com.webtoon.manamana.entity.webtoon.QComment;
import com.webtoon.manamana.entity.webtoon.QWebtoon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    /*댓글 전체 조회*/
    public List<Comment> findCommentAll(long webtoonId, Pageable pageable){

        QComment comment = QComment.comment;

        return queryFactory
                .selectFrom(comment)
                .where(comment.isDeleted.eq(false))
                .leftJoin(comment.user, QUser.user)
                .fetchJoin()
                .leftJoin(comment.webtoon, QWebtoon.webtoon)
                .fetchJoin()
                .where(comment.webtoon.id.eq(webtoonId))
                .orderBy()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    }

    /*댓글 단일 조회*/
    public Optional<Comment> findCommentById(long webtoonId, long userId, long commentId){

        QComment comment = QComment.comment;

        return Optional.ofNullable(
                queryFactory
                        .selectFrom(comment)
                        .where(comment.isDeleted.eq(false))
                        .leftJoin(comment.webtoon, QWebtoon.webtoon)
                        .fetchJoin()
                        .where(comment.webtoon.id.eq(webtoonId))
                        .leftJoin(comment.user, QUser.user)
                        .fetchJoin()
                        .where(comment.user.id.eq(userId), comment.id.eq(commentId))
                        .fetchOne());
    }


}
