package com.example.testapi.web;


import com.example.testapi.model.dto.EventDTO;
import com.example.testapi.model.dto.UpdateEventDTO;
import com.example.testapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
public class EventController {


    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable UUID uuid) {
        Optional<EventDTO> eventDTO = eventService.getEventByUuid(uuid);

        return eventDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{uuid}")
    public ResponseEntity<List<EventDTO>> getEventsByUserUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(eventService.getEventsByUserUuid(uuid));
    }
    @GetMapping("/hall/{uuid}")
    public ResponseEntity<List<EventDTO>> getEventsByHallUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(eventService.getEventsByHallUuid(uuid));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<EventDTO>> getFeaturedEvents() {
        return ResponseEntity.ok(eventService.getFeaturedEvents());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<EventDTO> deleteEvent(@PathVariable UUID uuid) {
        Optional<EventDTO> eventDTO = eventService.getEventByUuid(uuid);
        if (eventDTO.isPresent()) {
            eventService.deleteEventByUuid(eventDTO.get().getUuid());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<EventDTO> createEvent(
            @RequestBody EventDTO eventDTO,
            UriComponentsBuilder uriBuilder) {

        UUID newEventUuid = eventService.addEvent(eventDTO);

        return ResponseEntity.created(
                uriBuilder.path("/api/event/{newEventUuid}").build(newEventUuid)
        ).build();
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<UpdateEventDTO> updateEvent(
            @RequestBody UpdateEventDTO updateEventDTO,
            @PathVariable UUID uuid) {

        boolean updated = eventService.updateEvent(updateEventDTO, uuid);

        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }

}
