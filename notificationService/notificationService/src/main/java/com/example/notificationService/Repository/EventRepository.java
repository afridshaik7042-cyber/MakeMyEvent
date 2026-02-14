package com.example.notificationService.Repository;

import com.example.notificationService.Event;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    @Query("SELECT e.createdBy FROM Event e WHERE e.id = :eventId")
    String findCreatedById(@Param("eventId") UUID eventId);
}
