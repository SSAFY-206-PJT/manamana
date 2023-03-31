package com.manamana.crawling.repository;

import com.manamana.crawling.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
