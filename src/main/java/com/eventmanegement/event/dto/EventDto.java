package com.eventmanegement.event.dto;


import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private String eventId;
    private String name;
    private String location;
    private int ticketPrice;

    private List<TicketDto> tickets = new ArrayList<>();
}
