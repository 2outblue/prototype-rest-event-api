package com.example.testapi.init;

import com.example.testapi.model.entity.Event;
import com.example.testapi.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DBInit implements CommandLineRunner {

    private final EventRepository eventRepository;

    public DBInit(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (eventRepository.count() == 0) {
            initEvents();
        }
    }


    private void initEvents(){
        List<Event> events = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            events.add(new Event()
                    .setUuid(UUID.randomUUID())
                    .setDescription(String.format("Event # %d # Description", i))
                    .setName(String.format("Event @ %d @ Name", i))
                    .setDate(LocalDate.now())
                    .setTickets(10 * i)
                    .setHallId(UUID.randomUUID())
                    .setUserId(UUID.randomUUID())
            );
        }
        eventRepository.saveAll(events);
    }
}
