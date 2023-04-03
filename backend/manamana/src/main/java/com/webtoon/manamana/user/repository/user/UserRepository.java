package com.webtoon.manamana.user.repository.user;

import com.webtoon.manamana.entity.user.LoginProvider;
import com.webtoon.manamana.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByIdAndIsDeletedFalse(long userId);


}
