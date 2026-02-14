package com.event.Event.Repository;

import com.event.Event.Entity.EventEntity;
import com.event.Event.Model.EventStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<EventEntity, UUID> {
    List<EventEntity> findByCreatedBy(String username);

    void removeById(UUID uuid);

    List<EventEntity> findByEventStatus(EventStatus eventStatus);
}
