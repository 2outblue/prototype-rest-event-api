package com.example.testapi.model.mapper;

import com.example.testapi.model.dto.EventDTO;
import com.example.testapi.model.dto.UpdateEventDTO;
import com.example.testapi.model.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO toDto(Event event);
    Event toEntity(EventDTO eventDTO);

    @Mapping(target = "uuid", ignore = true)
    Event updateEntityFromDTO(@MappingTarget Event event, UpdateEventDTO updateEventDTO);

    Event updateEntity(Event event);
}
