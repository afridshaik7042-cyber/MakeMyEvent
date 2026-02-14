package com.event.Event.Repository;

import com.event.Event.Entity.FavouriteEvents;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavEventsRepository extends JpaRepository<FavouriteEvents, UUID> {
}
