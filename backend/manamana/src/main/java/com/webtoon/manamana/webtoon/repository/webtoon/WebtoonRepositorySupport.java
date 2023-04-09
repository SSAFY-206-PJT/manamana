package com.webtoon.manamana.webtoon.repository.webtoon;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.user.QUserWebtoon;
import com.webtoon.manamana.entity.webtoon.*;
import com.webtoon.manamana.webtoon.util.WebtoonFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class WebtoonRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public WebtoonRepositorySupport(JPAQueryFactory queryFactory) {
        super(Webtoon.class);
        this.queryFactory = queryFactory;
    }


    //in쿼리를 이용해서 페이징처리하여 id값 set으로 만드는 메서드
    public Page<Webtoon> findWebtoonAllPage(Set<Long> id,WebtoonFilterDTO webtoonFilterDTO, Pageable pageable){

        QWebtoon webtoon = QWebtoon.webtoon;

        List<Webtoon> webtoons = queryFactory
                .selectFrom(webtoon)
                .where(webtoon.id.in(id),webtoon.isDeleted.eq(false),
                        containsKey(webtoonFilterDTO.getKeyword()),
                                statusEq(webtoonFilterDTO.getStatusId()),
                                gradeEq(webtoonFilterDTO.getGradeId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Webtoon> limit = queryFactory
                .selectFrom(webtoon)
                .where(webtoon.id.in(id),
                        containsKey(webtoonFilterDTO.getKeyword()),
                        statusEq(webtoonFilterDTO.getStatusId()),
                        gradeEq(webtoonFilterDTO.getGradeId()));

        return PageableExecutionUtils.getPage(webtoons,pageable,() -> limit.fetch().size());
    }

    //페치조인과 정렬하는 메서드
    public List<Webtoon> findWebtoonAllJoinAndOrderBy(Set<Long> id,WebtoonFilterDTO webtoonFilterDTO){
        QWebtoon webtoon = QWebtoon.webtoon;

        return queryFactory
                .selectFrom(webtoon)
                .where(webtoon.isDeleted.eq(false),
                        webtoon.id.in(id))
                .leftJoin(webtoon.webtoonDays, QWebtoonDay.webtoonDay).fetchJoin()
                .leftJoin(webtoon.webtoonGenres, QWebtoonGenre.webtoonGenre).fetchJoin()
                .leftJoin(webtoon.webtoonAddition, QWebtoonAddition.webtoonAddition).fetchJoin()
                .leftJoin(webtoon.authors, QAuthor.author).fetchJoin()
                .orderBy(sortTypeOrder(webtoonFilterDTO.getSortType()))
                .distinct()
                .fetch();
    }






    // TODO : distinct 이케 쓰면 안됨 - 메모리에 올려서 중복제거를 하기 떄문에 in 쿼리를 이용해서 해결 할 수 있도록 바꿔야됨.
    public Page<Webtoon> findWebtoonAll(WebtoonFilterDTO webtoonFilterDTO, Pageable pageable){

        QWebtoon webtoon = QWebtoon.webtoon;

        List<Webtoon> webtoons = queryFactory
                .select(webtoon).distinct()
                .from(webtoon)
                .where(webtoon.isDeleted.eq(false),
                        webtoon.isDeleted.eq(false),
                        containsKey(webtoonFilterDTO.getKeyword()),
                        statusEq(webtoonFilterDTO.getStatusId()),
                        gradeEq(webtoonFilterDTO.getGradeId()))
                .leftJoin(webtoon.webtoonDays, QWebtoonDay.webtoonDay).fetchJoin().where(dayContain(webtoonFilterDTO.getDayId()))
                .leftJoin(webtoon.webtoonGenres, QWebtoonGenre.webtoonGenre).fetchJoin().where(genreContain(webtoonFilterDTO.getGenreId()))
                .leftJoin(webtoon.webtoonAddition, QWebtoonAddition.webtoonAddition).fetchJoin()
                .leftJoin(webtoon.authors, QAuthor.author).fetchJoin()
                .orderBy(sortTypeOrder(webtoonFilterDTO.getSortType()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //총 개수 세기
        JPAQuery<Webtoon> countQuery = queryFactory
                .select(webtoon).distinct()
                .from(webtoon)
                .where(webtoon.isDeleted.eq(false),
                        webtoon.isDeleted.eq(false),
                        containsKey(webtoonFilterDTO.getKeyword()),
                        statusEq(webtoonFilterDTO.getStatusId()),
                        gradeEq(webtoonFilterDTO.getGradeId()))
                .leftJoin(webtoon.webtoonDays, QWebtoonDay.webtoonDay).fetchJoin().where(dayContain(webtoonFilterDTO.getDayId()))
                .leftJoin(webtoon.webtoonGenres, QWebtoonGenre.webtoonGenre).fetchJoin().where(genreContain(webtoonFilterDTO.getGenreId()))
                .leftJoin(webtoon.webtoonAddition, QWebtoonAddition.webtoonAddition).fetchJoin()
                .leftJoin(webtoon.authors, QAuthor.author).fetchJoin();

        return PageableExecutionUtils.getPage(webtoons,pageable,() -> countQuery.fetch().size());

    }

    /*웹툰 상세정보*/
    public Optional<Webtoon> findWebtoonOne(long webtoonId){
        QWebtoon webtoon = QWebtoon.webtoon;
        return Optional.ofNullable(queryFactory
                .selectFrom(webtoon)
                .where(webtoon.isDeleted.eq(false), webtoon.id.eq(webtoonId))
                .leftJoin(webtoon.userWebtoons, QUserWebtoon.userWebtoon).fetchJoin()
                .leftJoin(webtoon.webtoonDays, QWebtoonDay.webtoonDay).fetchJoin()
                .leftJoin(webtoon.webtoonGenres, QWebtoonGenre.webtoonGenre).fetchJoin()
                .leftJoin(webtoon.webtoonAddition,QWebtoonAddition.webtoonAddition).fetchJoin()
                .leftJoin(webtoon.authors, QAuthor.author).fetchJoin()
                .leftJoin(webtoon.comment, QComment.comment).fetchJoin()
                .fetchOne());

    }

    /*키워드 검색*/
    private BooleanExpression containsKey(String keyword){

        if(keyword == null) return null;

        return QWebtoon.webtoon.name.contains(keyword).or(QWebtoon.webtoon.authors.any().name.contains(keyword));

    }

    /*연재여부*/
    private BooleanExpression statusEq(List<Integer> statusId){

        if(statusId == null || statusId.isEmpty()) return null;

        return QWebtoon.webtoon.statusId.in(statusId);
    }



    /*연령 등급*/
    private BooleanExpression gradeEq(List<Integer> gradeId){

        if(gradeId == null || gradeId.isEmpty()) return null;

        return QWebtoon.webtoon.gradeId.in(gradeId);
    }
    /*요일*/
    private BooleanExpression dayContain(List<Integer> dayId){

        if(dayId == null || dayId.isEmpty()) return null;

        return QWebtoon.webtoon.webtoonDays.any().codeId.in(dayId);

    }
    /*장르*/
    private BooleanExpression genreContain(List<Integer> genreId){

        if(genreId == null || genreId.isEmpty()) return null;

        return QWebtoon.webtoon.webtoonGenres.any().genre.id.in(genreId);
    }
    /*정렬 조건*/
    private OrderSpecifier<?> sortTypeOrder(Integer sortType){


        if(sortType == null){
            return new OrderSpecifier(Order.ASC,QWebtoon.webtoon.name);
        }

        //조회순
        else if(sortType.equals(1)){
            return new OrderSpecifier(Order.DESC,QWebtoon.webtoon.webtoonAddition.view);
        }
        //별점 순
        else if(sortType.equals(2)){
            return new OrderSpecifier(Order.DESC
                    ,QWebtoon.webtoon.webtoonAddition.totalScore.divide(QWebtoon.webtoon.webtoonAddition.scoreCount));
        }
        //댓글 순
        else if(sortType.equals(3)){
            return new OrderSpecifier(Order.DESC
                    ,QWebtoon.webtoon.comment.size());
        }

        else{
            return new OrderSpecifier(Order.ASC,QWebtoon.webtoon.name);
        }
    }





}
