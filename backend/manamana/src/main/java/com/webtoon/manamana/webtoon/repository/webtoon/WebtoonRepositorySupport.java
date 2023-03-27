package com.webtoon.manamana.webtoon.repository.webtoon;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.user.QUserWebtoon;
import com.webtoon.manamana.entity.webtoon.*;
import com.webtoon.manamana.entity.webtoon.codetable.QGenre;
import com.webtoon.manamana.webtoon.util.WebtoonListFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class WebtoonRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public WebtoonRepositorySupport(JPAQueryFactory queryFactory) {
        super(Webtoon.class);
        this.queryFactory = queryFactory;
    }

    public List<Webtoon> findWebtoonAll(WebtoonListFilter webtoonListFilter, Pageable pageable){

        QWebtoon webtoon = QWebtoon.webtoon;
        OrderSpecifier[] orderSpecifiers = sortTypeOrder(webtoonListFilter.getSortType());

        return queryFactory
                .selectFrom(webtoon)
                .where(webtoon.isDeleted.eq(false),
                        containsKey(webtoonListFilter.getKeyword()),
                        statusEq(webtoonListFilter.getStatusId()),
                        gradeEq(webtoonListFilter.getGradeId()))
                .leftJoin(webtoon.webtoonDays, QWebtoonDay.webtoonDay)
                .fetchJoin()
                .where(dayContain(webtoonListFilter.getDayId()))
                .leftJoin(webtoon.webtoonGenres, QWebtoonGenre.webtoonGenre)
                .fetchJoin()
                .where(genreContain(webtoonListFilter.getGenreId()))
                .leftJoin(webtoon.webtoonAddition,QWebtoonAddition.webtoonAddition)
                .fetchJoin()
                .leftJoin(webtoon.authors, QAuthor.author)
                .fetchJoin()
                .leftJoin(webtoon.comment, QComment.comment)
                .fetchJoin()
                .where(QComment.comment.isDeleted.eq(false))
                .orderBy(orderSpecifiers)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


    }

    /*웹툰 상세정보*/
    public Optional<Webtoon> findWebtoonOne(long userId, long webtoonId){
        QWebtoon webtoon = QWebtoon.webtoon;
        return Optional.ofNullable(queryFactory
                .selectFrom(webtoon)
                .where(webtoon.isDeleted.eq(false), webtoon.id.eq(webtoonId))
                .leftJoin(webtoon.userWebtoons, QUserWebtoon.userWebtoon)
                .fetchJoin()
                .where(webtoon.userWebtoons.any().user.id.eq(userId))
                .leftJoin(webtoon.webtoonDays, QWebtoonDay.webtoonDay)
                .fetchJoin()
                .leftJoin(webtoon.webtoonGenres, QWebtoonGenre.webtoonGenre)
                .fetchJoin()
                .leftJoin(webtoon.webtoonAddition,QWebtoonAddition.webtoonAddition)
                .fetchJoin()
                .leftJoin(webtoon.authors, QAuthor.author)
                .fetchJoin()
                .leftJoin(webtoon.comment, QComment.comment)
                .fetchJoin()
                .where(QComment.comment.isDeleted.eq(false))
                .fetchOne());

    }




    /*키워드 검색*/
    private BooleanExpression containsKey(String keyword){

        if(keyword == null) return null;

        return QWebtoon.webtoon.name.contains(keyword);

    }

    /*연재여부*/
    private BooleanExpression statusEq(Integer statusId){

        if(statusId == null) return null;

        return QWebtoon.webtoon.statusId.eq(statusId);
    }



    /*연령 등급*/
    private BooleanExpression gradeEq(Integer gradeId){

        if(gradeId == null) return null;

        return QWebtoon.webtoon.gradeId.eq(gradeId);
    }
    /*요일*/
    private BooleanExpression dayContain(Integer dayId){

        if(dayId == null) return null;

        return QWebtoon.webtoon.webtoonDays.any().codeId.eq(dayId);

    }
    /*장르*/
    private BooleanExpression genreContain(Integer genreId){

        if(genreId == null) return null;

        return QWebtoon.webtoon.webtoonGenres.any().genre.id.eq(genreId);
    }
    /*정렬 조건*/
    private OrderSpecifier[] sortTypeOrder(Integer sortType){

        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

        if(sortType == null){
            orderSpecifiers.add(new OrderSpecifier(Order.ASC,QWebtoon.webtoon.name));
        }

        //조회순
        else if(sortType.equals(1)){
            orderSpecifiers.add(new OrderSpecifier(Order.DESC,QWebtoon.webtoon.webtoonAddition.view));
        }
        //별점 순
        else if(sortType.equals(2)){
            orderSpecifiers.add(new OrderSpecifier(Order.DESC
                    ,QWebtoon.webtoon.webtoonAddition.totalScore.divide(QWebtoon.webtoon.webtoonAddition.scoreCount)));
        }
        //댓글 순
        else if(sortType.equals(3)){
            orderSpecifiers.add(new OrderSpecifier(Order.DESC
                    ,QWebtoon.webtoon.comment.size()));
        }

        else{
            orderSpecifiers.add(new OrderSpecifier(Order.ASC,QWebtoon.webtoon.name));
        }

        return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
    }




}
