package com.eventmanegement.event.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ticket {

    @Id
    private String ticketId;

    private Date bookingDate;

    private String ticketStatus;

    private int ticketPrice;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_Id")
    @JsonBackReference
    private Event event;


}
