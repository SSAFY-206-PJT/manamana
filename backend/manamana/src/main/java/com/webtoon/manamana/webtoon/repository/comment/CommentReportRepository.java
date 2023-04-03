package com.webtoon.manamana.webtoon.repository.comment;

import com.webtoon.manamana.entity.webtoon.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReportRepository extends JpaRepository<Report, Long> {
}
