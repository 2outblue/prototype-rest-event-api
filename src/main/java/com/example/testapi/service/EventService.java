package com.example.testapi.service;


import com.example.testapi.model.dto.EventDTO;
import com.example.testapi.model.entity.Event;
import com.example.testapi.model.mapper.EventMapper;
import com.example.testapi.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {



    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(eventMapper::toDto).collect(Collectors.toList());
    }

    public Optional<EventDTO> getEventByUuid(UUID uuid) {
        Optional<Event> eventEntity = eventRepository.findByUuid(uuid);

        if (eventEntity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(eventMapper.toDto(eventEntity.get()));
    }

    @Transactional
    public void deleteEventByUuid(UUID uuid) {
        eventRepository.deleteByUuid(uuid);
    }

    public UUID addEvent(EventDTO eventDTO) {
        Event event = eventMapper.toEntity(eventDTO);
        event.setUuid(UUID.randomUUID());

        eventRepository.save(event);
        return eventDTO.getUuid();
    }
}
