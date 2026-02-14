package com.example.notificationSender.Repository;

import com.example.notificationSender.NotificationEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {
    boolean existsByNotificationId(UUID uuid);
}
