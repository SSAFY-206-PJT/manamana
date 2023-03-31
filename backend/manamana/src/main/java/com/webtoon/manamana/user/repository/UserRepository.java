package com.webtoon.manamana.user.repository;

import com.webtoon.manamana.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
