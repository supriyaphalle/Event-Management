package com.eventmanegement.event.service;

import com.eventmanegement.event.dto.EventDto;
import com.eventmanegement.event.dto.PageableResponse;
import com.eventmanegement.event.dto.TicketDto;
import com.eventmanegement.event.dto.UserDto;

import java.awt.print.Pageable;
import java.util.List;

public interface EventService {

    public EventDto addEvent(EventDto event);

    public PageableResponse<EventDto> getAllEventBooking(int pageNumber, int pageSize, String sortBy, String sortDir);


    public TicketDto RegisterUserForEvent(String eventId, String userId);


//    public void deleteEventWithUserId(String userId);
//
//    public void deleteEventByEventId(String eventId);

}
