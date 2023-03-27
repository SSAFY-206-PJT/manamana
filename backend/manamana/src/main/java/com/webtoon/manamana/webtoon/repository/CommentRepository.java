package com.webtoon.manamana.webtoon.repository;

import com.webtoon.manamana.entity.webtoon.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
