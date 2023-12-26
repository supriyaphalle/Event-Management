package com.eventmanegement.event.repository;

import com.eventmanegement.event.entities.Event;
import com.eventmanegement.event.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findByEvent(Event event);


}
