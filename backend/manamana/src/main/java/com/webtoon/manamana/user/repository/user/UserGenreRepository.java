package com.webtoon.manamana.user.repository.user;

import com.webtoon.manamana.entity.user.UserGenre;
import com.webtoon.manamana.entity.user.UserGenreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGenreRepository extends JpaRepository<UserGenre, UserGenreId> {


}
