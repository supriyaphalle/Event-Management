package com.eventmanegement.event.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;

    private String name;

    private String email;

    private String contactNumber;

    private String password;

    private String about;
}
