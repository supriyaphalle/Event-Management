package com.eventmanegement.event.controller;

import com.eventmanegement.event.constants.AppConstants;
import com.eventmanegement.event.dto.EventDto;
import com.eventmanegement.event.dto.PageableResponse;
import com.eventmanegement.event.dto.TicketDto;
import com.eventmanegement.event.service.EventService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;


    @PostMapping
    public ResponseEntity<EventDto> addEvent(@RequestBody EventDto event) {
        EventDto eventDto = this.eventService.addEvent(event);
        return new ResponseEntity<>(eventDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<EventDto>> getAllEvent(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

        PageableResponse<EventDto> allEventBooking = this.eventService.getAllEventBooking(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allEventBooking, HttpStatus.OK);
    }


    @GetMapping("/{eventId}/user/{userId}")
    public ResponseEntity<TicketDto> bookATicketForUser(@PathVariable String eventId, @PathVariable String userId) {
        TicketDto ticketDto = this.eventService.RegisterUserForEvent(eventId, userId);
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }


//    @DeleteMapping("/user/{userId}")
//    public ResponseEntity<ApiResponseMessage> deleteEventByUserID(@PathVariable String userId) {
//        this.eventService.deleteEventWithUserId(userId);
//        ApiResponseMessage message = ApiResponseMessage.builder().message(AppConstants.DATA_DELETED)
//                .success(true).status(HttpStatus.OK).build();
//
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{eventId}")
//    public ResponseEntity<ApiResponseMessage> deleteEventByEventID(@PathVariable String eventId) {
//        this.eventService.deleteEventWithUserId(eventId);
//        ApiResponseMessage message = ApiResponseMessage.builder().message(AppConstants.DATA_DELETED)
//                .success(true).status(HttpStatus.OK).build();
//
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }

}
