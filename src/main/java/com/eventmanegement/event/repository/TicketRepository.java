package com.eventmanegement.event.repository;

import com.eventmanegement.event.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
