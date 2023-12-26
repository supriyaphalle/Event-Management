package com.eventmanegement.event.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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


    //    @JoinColumn(name = "ticket_Id")
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> tickets = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "event_Id")
//    private Event event;


}
