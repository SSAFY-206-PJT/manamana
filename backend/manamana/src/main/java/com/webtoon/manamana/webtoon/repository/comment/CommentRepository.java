package com.webtoon.manamana.webtoon.repository.comment;

import com.webtoon.manamana.entity.webtoon.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment,Long> {



}
