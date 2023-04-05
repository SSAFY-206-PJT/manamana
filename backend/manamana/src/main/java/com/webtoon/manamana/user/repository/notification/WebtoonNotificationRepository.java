package com.webtoon.manamana.user.repository.notification;

import com.webtoon.manamana.entity.user.WebtoonNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonNotificationRepository extends JpaRepository<WebtoonNotification,Long> {
}
