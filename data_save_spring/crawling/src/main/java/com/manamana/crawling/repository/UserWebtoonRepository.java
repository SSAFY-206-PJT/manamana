package com.manamana.crawling.repository;

import com.manamana.crawling.entity.user.UserWebtoon;
import com.manamana.crawling.entity.user.UserWebtoonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWebtoonRepository extends JpaRepository<UserWebtoon, UserWebtoonId> {
    
}
