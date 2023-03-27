package com.webtoon.manamana.user.repository.user;

import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.entity.user.UserWebtoon;
import com.webtoon.manamana.entity.user.UserWebtoonId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserWebtoonRepository extends JpaRepository<UserWebtoon, UserWebtoonId> {

    Optional<UserWebtoon> findByUserAndIsDeletedFalseAndIsLikedTrue(User user);
}
