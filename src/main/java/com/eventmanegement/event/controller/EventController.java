package com.eventmanegement.event.controller;

import com.eventmanegement.event.constants.AppConstants;

import com.eventmanegement.event.dto.*;
import com.eventmanegement.event.service.EventService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;


    @PostMapping("/")
    public ResponseEntity<EventDto> addEvent(@RequestBody EventDto event) {
        EventDto eventDto = this.eventService.addEvent(event);
        return new ResponseEntity<>(eventDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<PageableResponse<EventDto>> getAllEvent(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

        PageableResponse<EventDto> allEventBooking = this.eventService.getAllEventBooking(pageNumber, pageSize, sortBy, sortDir);

        System.out.println(allEventBooking.getContent());
        return new ResponseEntity<>(allEventBooking, HttpStatus.OK);
    }


    @PostMapping("/{eventId}/user/{userId}")
    public ResponseEntity<TicketDto> bookATicketForUser(@PathVariable String userId, @PathVariable String eventId) {
        TicketDto ticketDto = this.eventService.RegisterUserForEvent(eventId, userId);
        return new ResponseEntity<>(ticketDto, HttpStatus.CREATED);
    }


    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<TicketDto> getTicketByTicketId(@PathVariable String ticketId) {
        TicketDto ticketDto = this.eventService.getTicketByTicketId(ticketId);
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<ViewBookingRequest> getBookingDetails(@PathVariable String eventId) {
        ViewBookingRequest viewBookingRequest = this.eventService.viewBookingWithUser(eventId);
        return new ResponseEntity<>(viewBookingRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<ApiResponse> deleteEventById(@PathVariable String eventId) {
        String s = this.eventService.CancelEvent(eventId);
        ApiResponse message = ApiResponse.builder().message(AppConstants.DATA_DELETED)
                .success(true).build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
