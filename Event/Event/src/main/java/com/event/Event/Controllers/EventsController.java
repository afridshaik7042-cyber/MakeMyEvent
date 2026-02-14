package com.event.Event.Controllers;

import com.event.Event.Entity.EventEntity;
import com.event.Event.Model.Event;
import com.event.Event.Model.EventAddResponse;
import com.event.Event.Service.EventService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/event"})
public class EventsController {
    @Autowired
    public EventService eventService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping({"/createEvent"})
    public EventAddResponse addEvent(@RequestBody Event event) {
        System.out.println("ADD EVENT API CALLED");
        EventAddResponse response = this.eventService.addEvent(event);
        return response;
    }

    @GetMapping({"/getEvents"})
    public List<EventEntity> getEvents() {
        return this.eventService.getEvents();
    }

    @GetMapping({"/exploreEvent"})
    public ResponseEntity<EventEntity> exploreEvent(@RequestParam UUID uuid) {
        EventEntity event = this.eventService.exploreEvent(uuid);
        return ResponseEntity.ok(event);
    }

    @GetMapping({"/myEvents"})
    public ResponseEntity<List<EventEntity>> myEvents() {
        List<EventEntity> myEvents = this.eventService.getMyEvents();
        return ResponseEntity.ok(myEvents);
    }

    @PutMapping({"updateEvent"})
    public ResponseEntity<EventAddResponse> updateEvent(@RequestParam UUID uuid, @RequestBody Event event) {
        EventAddResponse eventAddResponse = this.eventService.updateEvent(uuid, event);
        return ResponseEntity.ok(eventAddResponse);
    }

    @DeleteMapping({"deleteEvent"})
    public ResponseEntity<EventAddResponse> deleteEvent(@PathVariable UUID uuid) {
        EventAddResponse eventAddResponse = this.eventService.deleteEvent(uuid);
        return ResponseEntity.ok(eventAddResponse);
    }

    @PostMapping({"/addToFav"})
    public ResponseEntity<EventAddResponse> addToFav(UUID uuid) {
        EventAddResponse eventAddResponse = this.eventService.addToFav(uuid);
        return ResponseEntity.ok(eventAddResponse);
    }
}
