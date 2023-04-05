package com.webtoon.manamana.webtoon.repository.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.manamana.entity.webtoon.QReport;
import com.webtoon.manamana.entity.webtoon.Report;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommentReportRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public CommentReportRepositorySupport(JPAQueryFactory queryFactory) {
        super(Report.class);
        this.queryFactory = queryFactory;
    }

    //댓글 + 유저 정보로 신고테이블 조회
    public Optional<Report> findReportByUserAndReport(long userId, long commentId){

        QReport report = QReport.report;

        return Optional.ofNullable(
                queryFactory
                        .selectFrom(report)
                        .where(report.userId.eq(userId),report.comment.id.eq(commentId))
                        .fetchOne()

        );

    }
}
