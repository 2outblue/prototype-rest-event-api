package com.example.testapi.model.dto;

import java.time.LocalDate;
import java.util.UUID;

public class UpdateEventDTO {


    //TODO - perhaps add ability to change date, which will be possible only with Admin privileges?
    private String description;
    private String name;

    private Integer tickets;


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


    public Integer getTickets() {
        return tickets;
    }

    public UpdateEventDTO setTickets(Integer tickets) {
        this.tickets = tickets;
        return this;
    }
}
