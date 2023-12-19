package com.eventmanegement.event.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//    @JoinColumn(name = "user_Id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Event event;


}
