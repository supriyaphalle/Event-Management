package com.eventmanegement.event.dto;


import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseMessage {

    private String message;

    private boolean success;

    private HttpStatus status;

}
