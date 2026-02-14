package com.event.Event.Service;

import com.event.Event.Entity.EventEntity;
import com.event.Event.Entity.FavouriteEvents;
import com.event.Event.ExceptionHandler.UnauthorizedException;
import com.event.Event.Model.Event;
import com.event.Event.Model.EventAddResponse;
import com.event.Event.Model.EventMessage;
import com.event.Event.Model.EventStatus;
import com.event.Event.Repository.EventsRepository;
import com.event.Event.Repository.FavEventsRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    public static final Logger log = LoggerFactory.getLogger(EventService.class);
    @Autowired
    public EventsRepository eventsRepository;
    @Autowired
    public EventKafka eventKafka;
    @Autowired
    public FavEventsRepository favEventsRepository;

    public EventAddResponse addEvent(Event event) {
        try {
            EventEntity eventEntity = new EventEntity();
            eventEntity.setId(UUID.randomUUID());
            eventEntity.setEventTime(event.eventTime);
            eventEntity.setCost("$ " + event.cost);
            eventEntity.setTitle(event.getTitle());
            eventEntity.setDescription(event.getDescription());
            eventEntity.setHostName(event.getHostName());
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            eventEntity.setEventStatus(EventStatus.UPCOMING);
            eventEntity.setCreatedBy(username);
            this.eventsRepository.save(eventEntity);
            EventMessage eventMessage = new EventMessage();
            eventMessage.setEventId(eventEntity.getId());
            eventMessage.setEventName(eventEntity.getTitle());
            this.eventKafka.sendEvent(eventMessage);
            EventAddResponse eventAddResponse = new EventAddResponse("200", "Event added sucessfully");
            return eventAddResponse;
        } catch (Exception e) {
            log.error("error", e);
            EventAddResponse eventAddResponse = new EventAddResponse("400", "Failed to add Event");
            return eventAddResponse;
        }
    }

    public List<EventEntity> getEvents() {
        List<EventEntity> listOfEvents = this.eventsRepository.findByEventStatus(EventStatus.UPCOMING);
        return listOfEvents;
    }

    public EventEntity exploreEvent(UUID uuid) {
        return (EventEntity)this.eventsRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<EventEntity> getMyEvents() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<EventEntity> myEvents = this.eventsRepository.findByCreatedBy(username);
        return myEvents;
    }

    public EventAddResponse updateEvent(UUID uuid, Event event) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        EventEntity eventEntity = (EventEntity)this.eventsRepository.findById(uuid).orElseThrow(() -> new NoSuchElementException("Event not found"));
        if (!username.equals(eventEntity.getCreatedBy())) {
            throw new UnauthorizedException("You cant update the event");
        } else {
            eventEntity.setTitle(event.title);
            eventEntity.setCost(event.cost);
            eventEntity.setEventTime(event.getEventTime());
            eventEntity.setDescription(event.getDescription());
            eventEntity.setHostName(event.getDescription());
            eventEntity.setEventStatus(EventStatus.UPCOMING);
            this.eventsRepository.save(eventEntity);
            EventAddResponse eventAddResponse = new EventAddResponse("200", "Event updated succesfully");
            return eventAddResponse;
        }
    }

    public EventAddResponse deleteEvent(UUID uuid) {
        try {
            EventEntity eventEntity = (EventEntity)this.eventsRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Event Not Found"));
            eventEntity.setEventStatus(EventStatus.CANCELLED);
            this.eventsRepository.save(eventEntity);
            EventAddResponse eventAddResponse = new EventAddResponse("200", "Event Cancelled");
            return eventAddResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public EventAddResponse addToFav(UUID uuid) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            FavouriteEvents favouriteEvents = new FavouriteEvents();
            favouriteEvents.setEventId(uuid);
            favouriteEvents.setEmail(username);
            favouriteEvents.setCreatedAt(LocalDateTime.now());
            this.favEventsRepository.save(favouriteEvents);
            EventAddResponse eventAddResponse = new EventAddResponse("200", "Added To Favourites");
            return eventAddResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
