package com.example.testapi.web;


import com.example.testapi.model.dto.EventDTO;
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
                uriBuilder.path("/api/event/{uuid}").build(newEventUuid)
        ).build();
    }

}
