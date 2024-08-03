package com.example.testapi.model.dto;

import java.time.LocalDate;
import java.util.UUID;

public class UpdateEventDTO {


    private String description;
    private String name;

    private int tickets;


    public String getDescription() {
        return description;
    }

    public UpdateEventDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateEventDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getTickets() {
        return tickets;
    }

    public UpdateEventDTO setTickets(int tickets) {
        this.tickets = tickets;
        return this;
    }
}
