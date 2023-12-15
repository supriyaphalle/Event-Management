package com.eventmanegement.event.dto;

import com.eventmanegement.event.entities.Event;
import com.eventmanegement.event.entities.User;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {
    private String ticketId;

    private Date bookingDate;

    private String ticketStatus;

    private int ticketPrice;

    private UserDto user;

    private EventDto event;
}
