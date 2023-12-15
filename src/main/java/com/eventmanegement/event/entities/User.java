package com.eventmanegement.event.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {


    @Id
    private String userId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phone")
    private String contactNumber;

    @Column(name = "user_password")
    private String password;


    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "ticket_Id")
    private Ticket ticket;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "event_Id")
//    private Event event;


}
