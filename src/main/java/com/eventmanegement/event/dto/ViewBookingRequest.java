package com.eventmanegement.event.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewBookingRequest {

    long totalBookings;

    List<UserDto> userDto;
}
