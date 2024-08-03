package com.example.testapi.repository;

import com.example.testapi.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
