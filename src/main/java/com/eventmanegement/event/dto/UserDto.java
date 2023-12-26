package com.eventmanegement.event.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private String password;
//    private List<TicketDto> tickets=new ArrayList<>();
}
