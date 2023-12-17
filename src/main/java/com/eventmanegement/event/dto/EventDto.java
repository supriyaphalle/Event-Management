package com.eventmanegement.event.dto;


import com.eventmanegement.event.entities.Ticket;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EventDto {

    private String eventId;

    private Date date;
    private String name;
    private String location;
    private int ticketQuantity;
    private int ticketPrice;

    private List<TicketDto> tickets;
}
