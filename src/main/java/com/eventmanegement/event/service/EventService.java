package com.eventmanegement.event.service;

import com.eventmanegement.event.dto.EventDto;
import com.eventmanegement.event.dto.PageableResponse;
import com.eventmanegement.event.dto.TicketDto;


public interface EventService {

    EventDto addEvent(EventDto event);

    PageableResponse<EventDto> getAllEventBooking(int pageNumber, int pageSize, String sortBy, String sortDir);


    TicketDto RegisterUserForEvent(String eventId, String userId);


//    public void deleteEventWithUserId(String userId);
//
//    public void deleteEventByEventId(String eventId);

}
