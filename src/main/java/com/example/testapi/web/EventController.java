package com.example.testapi.web;


import com.example.testapi.model.dto.EventDTO;
import com.example.testapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
